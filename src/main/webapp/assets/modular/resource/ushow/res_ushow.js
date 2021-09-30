layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var UShow = {
        tableId: "ushowTable"
    };

    /**
     * 初始化表格的列
     */
    UShow.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'ushowId', hide: true, title: '主键'},
            {field: 'fileName', sort: true, align: "center", title: '名称'},
            {field: 'fileStr', sort: true, align: "center", title: '描述'},
            {field: 'trainingTime', sort: true, align: "center", title: '时长'},
            {field: 'templateId', sort: true, align: "center", title: '模板id'},
            {field: 'bind1', sort: true, align: "center", title: 'bind1'},
            {field: 'bind6', sort: true, align: "center", title: 'bind6'},
            {field: 'audio', sort: true, align: "center", title: 'audio'},
            {field: 'video', sort: true, align: "center", title: 'video'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    UShow.search = function () {
        var queryData = {};
        queryData['fileName'] = $("#fileName").val();
        table.reload(UShow.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加uShow对话框
     */
    UShow.openAddDlg = function () {
        func.open({
            title: '添加uShow',
            content: Feng.ctxPath + '/view/res_ushow/add',
            tableId: UShow.tableId
        });
    };

    /**
     * 编辑uShow对话框
     *
     * @param data 点击按钮时候的行数据
     */
    UShow.openEditDlg = function (data) {
        func.open({
            title: '修改uShow',
            content: Feng.ctxPath + '/view/res_ushow/edit?ushowId=' + data.ushowId,
            tableId: UShow.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    UShow.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/ushow/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(UShow.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.message + "!");
            });
            request.set("ushowId", data.ushowId);
            request.start(true);
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + UShow.tableId,
        url: Feng.ctxPath + '/ushow/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: UShow.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        UShow.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        UShow.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + UShow.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            UShow.openEditDlg(data);
        } else if (layEvent === 'delete') {
            UShow.onDeleteItem(data);
        }
    });

});