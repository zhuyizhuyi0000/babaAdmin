layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var Code = {
        tableId: "codeTable"
    };

    /**
     * 初始化表格的列
     */
    Code.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'name', sort: true, align: "center", title: '仓库名称'},
            {field: 'site', sort: true, align: "center", title: '站点',
                templet:function (d){
                    if(d.site == 3){
                        res = "TW"
                    }else if(d.site == 1){
                        res = "COM"
                    }else if(d.site == 2){
                        res = "CO"
                    }else {
                        res = "CN"
                    }
                    return res;
                }
            },
            {field: 'url', sort: true, align: "center", title: '可访问的url'},
            {field: 'description', sort: true, align: "center", title: '描述'},
            {field: 'logic', sort: true, align: "center", title: '逻辑'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Code.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        table.reload(Code.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加code对话框
     */
    Code.openAddDlg = function () {
        func.open({
            title: '添加code',
            content: Feng.ctxPath + '/view/code_url/add',
            tableId: Code.tableId
        });
    };

    /**
     * 编辑code对话框
     *
     * @param data 点击按钮时候的行数据
     */
    Code.openEditDlg = function (data) {
        func.open({
            title: '修改code标签',
            content: Feng.ctxPath + '/view/code_url/edit?id=' + data.id,
            tableId: Code.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Code.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/code_url/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(Code.tableId);
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
        elem: '#' + Code.tableId,
        url: Feng.ctxPath + '/code_url/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Code.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Code.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Code.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Code.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Code.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Code.onDeleteItem(data);
        }
    });

});