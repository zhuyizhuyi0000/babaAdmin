/**
 * 添加视频
 */
layui.use(['form', 'admin', 'HttpRequest'], function () {
    var form = layui.form;
    var admin = layui.admin;
    var HttpRequest = layui.HttpRequest;
    var result;
    // 获取视频详情

    var vm = new Vue({
        data: {
            tag: [],
            forms: {},
            loading: false,
            options: [],
            value: [],
        },
        created:function () {
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
                    var request = new HttpRequest(Feng.ctxPath + "/tagVideo/findPage?pageNo=1&pageSize=10&name="+ query, 'get', function (data) {
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

    //表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var field = data.field;
        field.videoTag = vm.getData();
        data.field = field;
        var request = new HttpRequest(Feng.ctxPath + "/video/add", 'post', function (data) {
            admin.closeThisDialog();
            Feng.success("添加成功！");
            admin.putTempData('formOk', true);
        }, function (data) {
            admin.closeThisDialog();
            Feng.error("添加失败！" + data.message);
        });
        request.set(data.field);
        request.start(true);
    });

});