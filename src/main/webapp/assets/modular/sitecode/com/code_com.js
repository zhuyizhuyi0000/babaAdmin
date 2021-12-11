layui.use(['table', 'HttpRequest', 'func', 'form'], function () {

    var form = layui.form;
    var admin = layui.admin;
    var HttpRequest = layui.HttpRequest;
    var result;
    // 获取列表接口
    var request = new HttpRequest(Feng.ctxPath + "/code_com/findPageO?t=123", 'get');

    var vm = new Vue({
        data: {
            tableData: [],
            loading: false,
        },
        created:function () {
        },
        methods: {
            onSearch: function () {
                // console.log(vm, '--')
                vm.loading = true;
                vm.$forceUpdate();
                setTimeout(()=> {
                    vm.onGetData();
                },4000)
            },
            onGetData: function () {
                var vm = this
                var result = request.start();
                var data =  result.data;
                var keys = Object.keys(data);
                var tableData = [];
                for(const item of keys) {
                    var obj = {
                        label: item,
                        row: data[item],
                    }
                    tableData.push(obj);
                }
                vm.tableData = tableData;
                vm.loading = false;
            },
        },
        el: '#code-com',
    })



});