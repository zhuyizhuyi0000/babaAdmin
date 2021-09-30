layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var Doc = {
        tableId: "docTable"
    };

    /**
     * 初始化表格的列
     */
    Doc.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'docId', hide: true, title: '主键'},
            {field: 'fileName', sort: true, align: "center", title: '文档名称'},
            {field: 'fileSize', sort: true, align: "center", title: '文档大小'},
            {field: 'ext', sort: true, align: "center", title: '文档后缀'},
            {field: 'url', sort: true, align: "center", title: '文档链接'},
            {field: 'pdfUrl', sort: true, align: "center", title: '转码后链接'},
            {field: 'thumbInfo', sort: true, align: "center", title: '封面图地址'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Doc.search = function () {
        var queryData = {};
        queryData['fileName'] = $("#fileName").val();
        table.reload(Doc.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加文档对话框
     */
    Doc.openAddDlg = function () {
        func.open({
            title: '添加文档',
            content: Feng.ctxPath + '/view/res_doc/add',
            tableId: Doc.tableId
        });
    };

    /**
     * 编辑文档对话框
     *
     * @param data 点击按钮时候的行数据
     */
    Doc.openEditDlg = function (data) {
        func.open({
            title: '修改文档',
            content: Feng.ctxPath + '/view/res_doc/edit?docId=' + data.docId,
            tableId: Doc.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Doc.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/doc/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(Doc.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.message + "!");
            });
            request.set("docId", data.docId);
            request.start(true);
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Doc.tableId,
        url: Feng.ctxPath + '/doc/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Doc.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Doc.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Doc.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Doc.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Doc.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Doc.onDeleteItem(data);
        }
    });

});