layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var Video = {
        tableId: "videoTable"
    };

    /**
     * 初始化表格的列
     */
    Video.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'vidId', hide: true, title: '主键'},
            {field: 'fileName', sort: true, align: "center", title: '视频名称'},
            {field: 'fileSize', sort: true, align: "center", title: '视频大小'},
            {field: 'fileDuration', sort: true, align: "center", title: '视频长度'},
            {field: 'ext', sort: true, align: "center", title: '视频后缀'},
            {field: 'url', sort: true, align: "center", title: '视频链接'},
            {field: 'transcodingUrl', sort: true, align: "center", title: '转码后链接'},
            {field: 'thumbInfo', sort: true, align: "center", title: '封面图地址'},
            {field: 'videoTagList', sort: true, align: "center", title: '标签列表',
                width: 150,
                templet: function(row){
                    if(!row.videoTagList) {
                        return  ''
                    }
                   if(row.videoTagList.length === 0) {
                       return ''
                   }
                   const str = row.videoTagList.map(item=> `<div style="
                                border-radius: 10px;
                                box-sizing: border-box;
                                display: block;
                                font-size: 12px;
                                font-weight: 400;
                                height: 20px;
                                line-height: 20px;
                                overflow: hidden;
                                padding: 0 8px;
                                text-align: left;
                                background: #aecf55;
                                white-space: nowrap;">
                            ${item.name}</div>`);
                   return  `<div style="display: flex;">${str}</div>`
                }},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Video.search = function () {
        var queryData = {};
        queryData['fileName'] = $("#fileName").val();
        table.reload(Video.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加视频对话框
     */
    Video.openAddDlg = function () {
        func.open({
            title: '添加视频',
            content: Feng.ctxPath + '/view/res_video/add',
            tableId: Video.tableId
        });
    };

    /**
     * 编辑视频对话框
     *
     * @param data 点击按钮时候的行数据
     */
    Video.openEditDlg = function (data) {
        func.open({
            title: '修改视频',
            content: Feng.ctxPath + '/view/res_video/edit?vidId=' + data.vidId,
            tableId: Video.tableId
        });
    };

    /**
     * 添加单个视频对话框
     *
     * @param data 点击按钮时候的行数据
     */
    Video.openAddAccountDlg = function () {
        var queryData = {};
        if ($("#userName").val() && $("#passWord").val() && $("#pageNum").val() && $("#num").val() && $("#site").val()) {
            queryData['userName'] = $("#userName").val();
            queryData['passWord'] = $("#passWord").val();
            queryData['pageNum'] = $("#pageNum").val();
            queryData['num'] = $("#num").val();
            queryData['site'] = $("#site").val();
            func.open({
                title: '修改视频-获取',
                content: Feng.ctxPath + '/view/res_video/Oedit?userName=' + queryData['userName'] +'&passWord=' +queryData['passWord'] + '&pageNum=' +queryData['pageNum'] + '&num=' + queryData['num'] + '&site=' + queryData['site'],
                tableId: Video.tableId
            });
        }

    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Video.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/video/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(Video.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.message + "!");
            });
            request.set("vidId", data.vidId);
            request.start(true);
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Video.tableId,
        url: Feng.ctxPath + '/video/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Video.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Video.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Video.openAddDlg();
    });

    // 添加按钮点击事件
    $('#btnAddAccount').click(function () {
        Video.openAddAccountDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Video.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Video.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Video.onDeleteItem(data);
        }
    });

});