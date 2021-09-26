layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var $ = layui.$;
    var table = layui.table;
    var HttpRequest = layui.HttpRequest;
    var func = layui.func;
    var form = layui.form;

    /**
     * 初始化参数
     */
    var Car = {
        tableId: "carTable"
    };

    /**
     * 初始化表格的列
     */
    Car.initColumn = function () {
        return [[
            {type: 'radio'},
            {field: 'carId', hide: true, title: '主键'},
            {field: 'carName', sort: true, align: "center", title: '车辆名称'},
            {
                field: 'carType', sort: true, align: "center", title: '车辆类型', templet: function (data) {
                    if (data.carType === 1) {
                        return '轿车';
                    } else {
                        return '货车';
                    }
                }
            },
            {field: 'carColor', sort: true, align: "center", title: '车辆颜色'},
            {field: 'carPrice', sort: true, align: "center", title: '车辆价格'},
            {field: 'manufacturer', sort: true, align: "center", title: '制造商'},
            {field: 'createTime', sort: true, align: "center", title: '创建时间'},
            {field: 'updateTime', sort: true, align: "center", title: '更新时间'},
            {align: 'center', toolbar: '#tableBar', title: '操作'}
        ]];
    };

    /**
     * 点击查询按钮
     */
    Car.search = function () {
        var queryData = {};
        queryData['carName'] = $("#carName").val();
        table.reload(Car.tableId, {
            where: queryData, page: {curr: 1}
        });
    };

    /**
     * 添加车辆对话框
     */
    Car.openAddDlg = function () {
        func.open({
            title: '添加车辆',
            content: Feng.ctxPath + '/view/car/add',
            tableId: Car.tableId
        });
    };

    /**
     * 编辑车辆对话框
     *
     * @param data 点击按钮时候的行数据
     */
    Car.openEditDlg = function (data) {
        func.open({
            title: '修改车辆',
            content: Feng.ctxPath + '/view/car/edit?carId=' + data.carId,
            tableId: Car.tableId
        });
    };

    /**
     * 点击删除
     *
     * @param data 点击按钮时候的行数据
     */
    Car.onDeleteItem = function (data) {
        var operation = function () {
            var request = new HttpRequest(Feng.ctxPath + "/car/delete", 'post', function (data) {
                Feng.success("删除成功!");
                table.reload(Car.tableId);
            }, function (data) {
                Feng.error("删除失败!" + data.message + "!");
            });
            request.set("carId", data.carId);
            request.start(true);
        };
        Feng.confirm("是否删除?", operation);
    };

    // 渲染表格
    var tableResult = table.render({
        elem: '#' + Car.tableId,
        url: Feng.ctxPath + '/car/findPage',
        page: true,
        height: "full-158",
        cellMinWidth: 100,
        cols: Car.initColumn(),
        request: {pageName: 'pageNo', limitName: 'pageSize'},
        parseData: Feng.parseData
    });

    // 搜索按钮点击事件
    $('#btnSearch').click(function () {
        Car.search();
    });

    // 添加按钮点击事件
    $('#btnAdd').click(function () {
        Car.openAddDlg();
    });

    // 工具条点击事件
    table.on('tool(' + Car.tableId + ')', function (obj) {
        var data = obj.data;
        var layEvent = obj.event;

        if (layEvent === 'edit') {
            Car.openEditDlg(data);
        } else if (layEvent === 'delete') {
            Car.onDeleteItem(data);
        }
    });

});