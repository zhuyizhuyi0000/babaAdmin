layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var Pd = {
        tableId: "pdTable"
    };

    /**
     * 初始化表格的列
     */
    Pd.initColumn = function () {
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
    Pd.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        table.reload(Pd.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加pd对话框
     */
    Pd.openAddDlg = function () {
        func.open({
            title: '添加pd账号',
            content: Feng.ctxPath + '/view/acc_pd/add',
            tableId: Pd.tableId
        });
    };

    /**
     * 编辑pd对话框
     *
     * @param data 点击按钮时候的行数据
     */
    Pd.openEditDlg = function (data) {
        func.open({
            title: '修改pd账号',
            content: Feng.ctxPath + '/view/acc_pd/edit?id=' + data.id,
            tableId: Pd.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Pd.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/pd/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(Pd.tableId);
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
        elem: '#' + Pd.tableId,
        url: Feng.ctxPath + '/pd/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Pd.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Pd.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Pd.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Pd.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Pd.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Pd.onDeleteItem(data);
        }
    });

});