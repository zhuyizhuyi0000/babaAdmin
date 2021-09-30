layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var TagDoc = {
        tableId: "tagdocTable"
    };

    /**
     * 初始化表格的列
     */
    TagDoc.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'name', sort: true, align: "center", title: '文档标签名称'},
            {field: 'description', sort: true, align: "center", title: '文档标签描述'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    TagDoc.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        table.reload(TagDoc.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加文档标签对话框
     */
    TagDoc.openAddDlg = function () {
        func.open({
            title: '添加文档标签',
            content: Feng.ctxPath + '/view/tagDoc/add',
            tableId: TagDoc.tableId
        });
    };

    /**
     * 编辑文档标签对话框
     *
     * @param data 点击按钮时候的行数据
     */
    TagDoc.openEditDlg = function (data) {
        func.open({
            title: '修改文档标签',
            content: Feng.ctxPath + '/view/tagDoc/edit?id=' + data.id,
            tableId: TagDoc.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    TagDoc.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/tagDoc/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(TagDoc.tableId);
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
        elem: '#' + TagDoc.tableId,
        url: Feng.ctxPath + '/tagDoc/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: TagDoc.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        TagDoc.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        TagDoc.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + TagDoc.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            TagDoc.openEditDlg(data);
        } else if (layEvent === 'delete') {
            TagDoc.onDeleteItem(data);
        }
    });

});