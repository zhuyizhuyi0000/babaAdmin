package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.controller.DocController;
import cn.stylefeng.guns.modular.resource.entity.TagLinkDoc;
import cn.stylefeng.guns.modular.resource.entity.TagDoc;
import cn.stylefeng.guns.modular.resource.entity.Doc;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.DocMapper;
import cn.stylefeng.guns.modular.resource.pojo.OnlineRequest;
import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import cn.stylefeng.guns.modular.resource.service.OnlineService;
import cn.stylefeng.guns.modular.resource.service.TagLinkDocService;
import cn.stylefeng.guns.modular.resource.service.TagDocService;
import cn.stylefeng.guns.modular.resource.service.DocService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** * 文档管理业务实现层   */
@Slf4j
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {
    
    @Resource
    private TagLinkDocService tagLinkDocService;
    @Resource
    private TagDocService tagDocService;
    @Resource
    private OnlineService onlineService;

    @Override
    public Long add(DocRequest docRequest){
        Doc doc = new Doc();
        BeanUtil.copyProperties(docRequest,doc);
        this.save(doc);
        //如果绑定tag字段不是空的话
//        log.info(docRequest.toString());
//        log.info(doc.toString());
        if (docRequest.getDocTag()!=null && !docRequest.getDocTag().isEmpty()){
//            log.info("66:"+docRequest.getDocTag().toString());
            tagLinkDocService.add(docRequest.getDocTag(),doc.getDocId());
        }
        return doc.getDocId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long del(DocRequest docRequest){
        Doc doc = this.queryDoc(docRequest);
        this.removeById(doc.getDocId());
        //去删tag关联表
        tagLinkDocService.del(doc.getDocId(),1); //1 resource；2tag
        return doc.getDocId();
    }

    @Override
    public Long edit(DocRequest docRequest){
        Doc doc=this.queryDoc(docRequest);
        BeanUtil.copyProperties(docRequest,doc);
        this.updateById(doc);
        //先清空这个关联表 然后再重新进行绑定
        tagLinkDocService.del(doc.getDocId(),1); //1 resource；2tag
        if (docRequest.getDocTag()!=null && !docRequest.getDocTag().isEmpty()){
            tagLinkDocService.add(docRequest.getDocTag(),doc.getDocId());
        }
        return doc.getDocId();
    }

    @Override
    public Doc detail(DocRequest docRequest){
        Doc doc=this.queryDoc(docRequest);
        //把单个的对应的taglist取出来 加进去
//        List<Long> docTagList = tagLinkDocService.findTagList(docRequest.getDocId(),1);
//        doc.setDocTag(docTagList);

        //直接返tagDoc对象
        List<TagDoc> docTagMapList = tagLinkDocService.findTagMapList(docRequest.getDocId(),1);
        doc.setDocTagList(docTagMapList);

        return doc;
    }

    /** *通过传username和password 把结果都拿过来   */
    @Override
    public List<Doc> findPageDetail(OnlineRequest onlineRequest){
        String userName = onlineRequest.getUserName();
        String passWord = onlineRequest.getPassWord();
        Integer pageNum = onlineRequest.getPageNum();
        Integer mode = onlineRequest.getMode();
        Integer site = onlineRequest.getSite();
        if(ObjectUtil.isEmpty(pageNum)){
            pageNum=1;
        }
        if(ObjectUtil.isEmpty(mode)){
            mode=1;
        }
        if(ObjectUtil.isEmpty(site)){
            site=1;
        }
        List<Doc> allDocList = onlineService.findPageDetailDoc(userName,passWord,pageNum,mode,site);
        return allDocList;
    }

    @Override
    public PageResult<Doc> findPage(DocRequest docRequest){
        //拿到了Doc的集合列表
//        log.info(docRequest.toString());
//        LambdaQueryWrapper<Doc> wrapper = this.createWrapper(docRequest);
//        Page<Doc> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        //和下面一样

        List<Doc> docList = findList(docRequest);
        Page<Doc> sysRolePage1 = PageFactory.defaultPage();
        int current = (int)sysRolePage1.getCurrent();
        int size = (int)sysRolePage1.getSize();
        int count =docList.size();
        List<Doc> pageList = new ArrayList<>();
        //计算当前页第一条数据的下标
        int currId = current>1 ? (current-1) * size:0;
        for(int i=0;i<size && i<count - currId;i++){
            pageList.add(docList.get(currId+i));
        }
//        sysRolePage1.setSize(size);
//        sysRolePage1.setCurrent(current);
        sysRolePage1.setTotal(count);
        //计算分页总页数
        sysRolePage1.setPages(count % size==0 ? count/size:count/size+1);
        sysRolePage1.setRecords(pageList);
        return PageResultFactory.createPageResult(sysRolePage1);
    }


    @Override
    public List<Doc> findList(DocRequest docRequest){
        //拿到了Doc的集合列表
        LambdaQueryWrapper<Doc> wrapper = this.createWrapper(docRequest);
        //所有doc的集合 allDocList
        List<Doc> allDocList = this.list(wrapper);
//        log.info("findlist里 第一次拿全信息："+allDocList);

        if(allDocList.size()!=0){
            //把allDocList转换成resourceID docId→ docList
            List<Long> docList = new ArrayList<>();
            for (int i=0;i<allDocList.size();i++){
                docList.add(allDocList.get(i).getDocId());
            }
//            log.info("所有resource集合： "+docList);

            //拿到TagLinkDoc的一个集合，包含了所有符合条件的
            List<TagLinkDoc> sumlist1 = tagLinkDocService.findAllTagList(docList,1);
            //            log.info("拿到tagLinkDoc的所有符合条件的集合"+sumlist1);

            //再把这个关联的list做一个tagid的集合，去查一遍  返回一个map<tagid,tagname>
            List<Long> tagAllList = new ArrayList<>();
            for(int a=0;a<sumlist1.size();a++){
                if(!tagAllList.contains(sumlist1.get(a).getTagId())){
                    tagAllList.add(sumlist1.get(a).getTagId());
                }
            }
//            log.info("tagId的集合: "+tagAllList.toString());

            List<TagDoc> tagList1 = tagDocService.findAllTagNameList(tagAllList);
            //搞成map 一个id一个name
            Map<Long,String> map = new HashMap<>();
            if(tagList1.size()>0){
                for(int i=0;i<tagList1.size();i++){
                    map.put(tagList1.get(i).getId(),tagList1.get(i).getName());
                }
            }

            //再给他塞放进每个doc里去
            for (int j=0;j<allDocList.size();j++){
                //先要再循环一下总集合sumlist1，把每个doc对应的tag弄成一个list onetaglist
                List<TagDoc> oneTagList = new ArrayList<>();
                //循环TagLinkDoc的集合sumlist1，取出resourceid相等的
                for(int k=0;k<sumlist1.size();k++){
                    if(allDocList.get(j).getDocId().equals(sumlist1.get(k).getResourceId())){
                        TagDoc tagDoc3 = new TagDoc();
                        tagDoc3.setId(sumlist1.get(k).getTagId());
                        tagDoc3.setName(map.get(sumlist1.get(k).getTagId()));
                        oneTagList.add(tagDoc3);
//                        log.info("!!"+oneTagList.toString());
                    }
                }
                allDocList.get(j).setDocTagList(oneTagList);

            }
        }
        return allDocList;
    }


    /** *获取视频信息   */
    private Doc queryDoc(DocRequest docRequest){
        Doc doc = this.getById(docRequest.getDocId());
        if(ObjectUtil.isEmpty(doc)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return doc;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Doc> createWrapper(DocRequest docRequest){
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();

        String docName = docRequest.getFileName();
        queryWrapper.like(ObjectUtil.isNotEmpty(docName),Doc::getFileName,docName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(Doc::getCreateTime);

        return queryWrapper;
    }

    /** *通过传username和password 把结果都拿过来  只拿一条   */
    @Override
    public Doc findPageOneDetail(OnlineRequest onlineRequest){
        String userName = onlineRequest.getUserName();
        String passWord = onlineRequest.getPassWord();
        Integer pageNum = onlineRequest.getPageNum();
        Integer mode = onlineRequest.getMode();
        Integer num = onlineRequest.getNum();
        Integer site = onlineRequest.getSite();

        if(ObjectUtil.isEmpty(pageNum)){
            pageNum=1;
        }
        if(ObjectUtil.isEmpty(mode)){
            mode=1;
        }
        if(ObjectUtil.isEmpty(num)){
            num=0;
        }
        if(ObjectUtil.isEmpty(site)){
            site=1;
        }
        Doc doc = onlineService.findPageOneDetailDoc(userName,passWord,pageNum,num,mode,site);
        return doc;
    }
}
