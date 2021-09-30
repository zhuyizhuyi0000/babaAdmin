layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var TagVideo = {
        tableId: "tagvideoTable"
    };

    /**
     * 初始化表格的列
     */
    TagVideo.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'id', hide: true, title: '主键'},
            {field: 'name', sort: true, align: "center", title: '视频标签名称'},
            {field: 'description', sort: true, align: "center", title: '视频标签描述'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    TagVideo.search = function () {
        var queryData = {};
        queryData['name'] = $("#name").val();
        table.reload(TagVideo.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加视频标签对话框
     */
    TagVideo.openAddDlg = function () {
        func.open({
            title: '添加视频标签',
            content: Feng.ctxPath + '/view/tagVideo/add',
            tableId: TagVideo.tableId
        });
    };

    /**
     * 编辑视频标签对话框
     *
     * @param data 点击按钮时候的行数据
     */
    TagVideo.openEditDlg = function (data) {
        func.open({
            title: '修改视频标签',
            content: Feng.ctxPath + '/view/tagVideo/edit?id=' + data.id,
            tableId: TagVideo.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    TagVideo.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/tagVideo/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(TagVideo.tableId);
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
        elem: '#' + TagVideo.tableId,
        url: Feng.ctxPath + '/tagVideo/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: TagVideo.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        TagVideo.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        TagVideo.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + TagVideo.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            TagVideo.openEditDlg(data);
        } else if (layEvent === 'delete') {
            TagVideo.onDeleteItem(data);
        }
    });

});