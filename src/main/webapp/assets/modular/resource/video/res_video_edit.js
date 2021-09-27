/**
 * 编辑视频
 */
layui.use(['form', 'admin', 'HttpRequest'], function () {
    var form = layui.form;
    var admin = layui.admin;
    var HttpRequest = layui.HttpRequest;

    // 获取视频详情
    var request = new HttpRequest(Feng.ctxPath + "/video/detail?vidId=" + Feng.getUrlParam("vidId"), 'get');
    var result = request.start();

    form.val('videoForm', result.data);

    // 表单提交事件
    form.on('submit(btnSubmit)', function (data) {
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