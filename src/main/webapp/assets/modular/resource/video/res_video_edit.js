/**
 * 编辑视频
 */
layui.use(['form', 'admin', 'HttpRequest'], function () {
    var form = layui.form;
    var admin = layui.admin;
    var HttpRequest = layui.HttpRequest;
    var result;
    // 获取视频详情
    var request = new HttpRequest(Feng.ctxPath + "/video/detail?vidId=" + Feng.getUrlParam("vidId"), 'get');


    var vm = new Vue({
        data: {
            tag: [],
            forms: {},
        },
        created:function () {
            result = request.start();
            layui.form.val('videoForm', result.data);
            this.forms = result.data;
            this.tag = result.data.videoTagList;
        },
        methods: {
            getData: function (){
                if(this.tag.length===0) {
                    return  [];
                }
                return this.tag.map((item)=> (item.id))
            },
        },
        el: '#layui-app',
    })

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
        var field = data.field;
        field.videoTag = vm.getData();
        data.field = field;
        var request = new HttpRequest(Feng.ctxPath + "/video/edit", 'post', function (data) {
            Feng.success("修改成功!");
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