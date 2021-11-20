package cn.stylefeng.guns.modular.resource.service.impl;

import cn.stylefeng.guns.modular.resource.entity.TagLinkDoc;
import cn.stylefeng.guns.modular.resource.entity.TagDoc;
import cn.stylefeng.guns.modular.resource.mapper.TagLinkDocMapper;
import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import cn.stylefeng.guns.modular.resource.service.TagLinkDocService;
import cn.stylefeng.guns.modular.resource.service.TagDocService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/** * 文档管理业务实现层   */
@Slf4j
@Service
public class TagLinkDocServiceImpl extends ServiceImpl<TagLinkDocMapper, TagLinkDoc> implements TagLinkDocService {

    @Resource
    private TagDocService tagDocService;

    @Override
    public void add(DocRequest docRequest){
        TagLinkDoc tagLinkDoc = new TagLinkDoc();
//        BeanUtil.copyProperties(docRequest,tagLinkDoc);
//        this.save(tagLinkDoc);
    }

    //批量保存
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(List<Long> list1,Long id){
        List<TagLinkDoc> tagLinkDocList = new ArrayList<>();
        for(Long tagId:list1){
            TagLinkDoc newTagLinkDoc = new TagLinkDoc();
            newTagLinkDoc.setResourceId(id);
            newTagLinkDoc.setTagId(tagId);
            tagLinkDocList.add(newTagLinkDoc);
        }
//        log.info(tagLinkDocList.toString());
        this.saveBatch(tagLinkDocList);

    }

    //使用map删除   主键id集合list可以用removeByIds
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(Long id,int num){
        HashMap<String,Object> map = new HashMap<>();
        if (num==1){
            map.put("resource_id",id);
            this.removeByMap(map);
        }else if(num==2){
            map.put("tag_id",id);
            this.removeByMap(map);
        }
    }

    @Override
    public List<TagLinkDoc> findList(Long id,int num){
        LambdaQueryWrapper<TagLinkDoc> wrapper = this.createWrapper(id,num);
        return this.list(wrapper);
    }

    //返回只有tagid的list    把对象的list取出只有tagid  1==用resourceid去getTagId
    @Override
    public List<Long> findTagList(Long id,int num){
        LambdaQueryWrapper<TagLinkDoc> wrapper = this.createWrapper(id,num);
        List<TagLinkDoc> tagLinkDocList = this.list(wrapper);
        List<Long> docTagList = new ArrayList<>();
        if(tagLinkDocList.size()==0){return docTagList;}
        for(int i=0;i<tagLinkDocList.size();i++){
            if(num==1){
                docTagList.add(tagLinkDocList.get(i).getTagId());
            }else {
                docTagList.add(tagLinkDocList.get(i).getResourceId());
            }

        }
        return docTagList;
    }

    //返回一个map  1 resourceId   2tag id
    @Override
    public List<TagDoc> findTagMapList(Long id, int num){
        LambdaQueryWrapper<TagLinkDoc> wrapper = this.createWrapper(id,num);
        List<TagLinkDoc> tagLinkDocList = this.list(wrapper);
        List<TagDoc> docTagList = new ArrayList<TagDoc>();

        if(tagLinkDocList.size()==0){return docTagList;}
        for(int i=0;i<tagLinkDocList.size();i++){
            TagDoc tagDoc =new TagDoc();
            if(num==1){
                String name = tagDocService.getName(tagLinkDocList.get(i).getTagId());
                tagDoc.setId(tagLinkDocList.get(i).getTagId());
                tagDoc.setName(name);
                docTagList.add(tagDoc);
            }else {
                String name = tagDocService.getName(id);
                tagDoc.setId(id);
                tagDoc.setName(name);
                docTagList.add(tagDoc);
            }
        }
        return docTagList;
    }

    //一次性把所有sql in的都查出来，避免查多次了
    @Override
    public List<TagLinkDoc> findAllTagList(List<Long> list,int num){
        LambdaQueryWrapper<TagLinkDoc> wrapper = this.createAllWrapper(list,num);
        return this.list(wrapper);
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<TagLinkDoc> createWrapper(Long id,int num){
        LambdaQueryWrapper<TagLinkDoc> queryWrapper = new LambdaQueryWrapper<>();
        if(num==1){
            queryWrapper.eq(TagLinkDoc::getResourceId, id);
        }else{
            queryWrapper.eq(TagLinkDoc::getTagId, id);
        }
//        queryWrapper.like(ObjectUtil.isNotEmpty(docName),Doc::getFileName,docName);

        /** * 根据创建时间倒序排序  */
        queryWrapper.orderByDesc(TagLinkDoc::getCreateTime);

        return queryWrapper;
    }

    /** * 创建查询wrapper 拿list进去  */
    private LambdaQueryWrapper<TagLinkDoc> createAllWrapper(List<Long> list,int num){
        LambdaQueryWrapper<TagLinkDoc> queryWrapper = new LambdaQueryWrapper<>();
        if(num==1){
            queryWrapper.in(TagLinkDoc::getResourceId, list);
        }else{
            queryWrapper.in(TagLinkDoc::getTagId, list);
        }
//        queryWrapper.like(ObjectUtil.isNotEmpty(docName),Doc::getFileName,docName);

        return queryWrapper;
    }
}
