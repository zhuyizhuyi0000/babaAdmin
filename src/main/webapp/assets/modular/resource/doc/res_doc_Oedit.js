/**
 * 编辑文档
 */
layui.use(['form', 'admin', 'HttpRequest'], function () {
    var form = layui.form;
    var admin = layui.admin;
    var HttpRequest = layui.HttpRequest;
    var result;
    // 获取文档详情
    var request = new HttpRequest(Feng.ctxPath + "/doc/findPageOneDetail?userName=" + Feng.getUrlParam("userName") + "&passWord=" + Feng.getUrlParam("passWord") + "&pageNum=" +Feng.getUrlParam("pageNum") + "&num=" +Feng.getUrlParam("num") + "&mode=2" + "&site=" + +Feng.getUrlParam("site"), 'get');
    // var request = new HttpRequest(Feng.ctxPath + "/doc/detail?vidId=" + Feng.getUrlParam("vidId"), 'get');

    var vm = new Vue({
        data: {
            tag: [],
            forms: {},
            loading: false,
            options: [],
            value: [],
        },
        created:function () {
            result = request.start();
            layui.form.val('docForm', result.data);
            this.forms = result.data;
            // this.tag = result.data.docTagList;
            // this.value =  result.data.docTagList.map(item=> { return item.id});
        },
        methods: {
            getData: function (){
                if(this.tag.length===0) {
                    return  [];
                }
                return this.tag.map((item)=> (item.id))
            },
            deleteItem: function (item) {
                this.tag = this.tag.filter( value=> { return value.id != item.id});
                this.value = this.value.filter(values => item.id != values);
            },
            removeTag: function (item) {
                this.tag = this.tag.filter( value=> { return value.id != item});
                this.value = this.value.filter(values => item != values);
            },
            remoteMethod(query) {
                if (query !== '') {
                    this.loading = true;
                    var that = this;
                    var request = new HttpRequest(Feng.ctxPath + "/tagDoc/findPage?pageNo=1&pageSize=10&name="+ query, 'get', function (data) {
                        that.loading = false;
                        var rows = data.data.rows;
                        var tagId = that.tag.map(item=> item.id);
                        rows = rows.filter(item=> !tagId.includes(item.id))
                        that.tag = that.tag.concat(rows);
                    });
                    request.start();
                } else {
                    this.options = [];
                }
            }
        },
        el: '#layui-app',
    })

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var field = data.field;
        field.docTag = vm.getData();
        data.field = field;
        var request = new HttpRequest(Feng.ctxPath + "/doc/add", 'post', function (data) {
            Feng.success("添加成功!");
            admin.putTempData('formOk', true);
            admin.closeThisDialog();
        }, function (data) {
            Feng.error("修改失败!" + data.message);
            admin.closeThisDialog();
        });
        request.set(data.field);
        request.start(true);
    });

});