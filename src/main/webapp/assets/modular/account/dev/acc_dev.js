layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var Dev = {
        tableId: "devTable"
    };

    /**
     * 初始化表格的列
     */
    Dev.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'name', sort: true, align: "center", title: '名称'},
            {field: 'env', sort: true, align: "center", title: '环境'},
            {field: 'loginName', sort: true, align: "center", title: '用户名'},
            {field: 'password', sort: true, align: "center", title: '密码'},
            {field: 'description', sort: true, align: "center", title: '描述'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Dev.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        table.reload(Dev.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加dev对话框
     */
    Dev.openAddDlg = function () {
        func.open({
            title: '添加dev账号',
            content: Feng.ctxPath + '/view/acc_dev/add',
            tableId: Dev.tableId
        });
    };

    /**
     * 编辑dev对话框
     *
     * @param data 点击按钮时候的行数据
     */
    Dev.openEditDlg = function (data) {
        func.open({
            title: '修改dev账号',
            content: Feng.ctxPath + '/view/acc_dev/edit?id=' + data.id,
            tableId: Dev.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Dev.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/dev/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(Dev.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.message + "!");
            });
            request.set("id", data.id);
            request.start(true);
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Dev.tableId,
        url: Feng.ctxPath + '/dev/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Dev.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Dev.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Dev.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Dev.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Dev.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Dev.onDeleteItem(data);
        }
    });

});