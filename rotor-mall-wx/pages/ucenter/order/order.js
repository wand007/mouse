var util = require('../../../utils/util.js');
var api = require('../../../config/api.js');

Page({
    data: {
        orderList: [],
        showType: 0,
        pageNum: 0,
        pageSize: 10,
        totalPages: 0
    },
    onLoad: function (options) {
        // 页面初始化 options为页面跳转所带来的参数
        let that = this
        try {
            var tab = wx.getStorageSync('tab');

            this.setData({
                showType: tab
            });
        } catch (e) {
        }

    },
    getOrderList() {
        let that = this;
        util.request(api.OrderList, {
            showType: that.data.showType,
            pageNum: that.data.pageNum,
            pageSize: that.data.pageSize
        }).then(function (res) {
            if (res.statusCode === 10000) {
                console.log(res.data);
                that.setData({
                    orderList: that.data.orderList.concat(res.data.list),
                    totalPages: res.data.totalPageCount
                });
            }
        });
    },
    onReachBottom() {
        if (this.data.totalPages - 1 > this.data.pageNum) {
            this.setData({
                pageNum: this.data.pageNum + 1
            });
            this.getOrderList();
        } else {
            wx.showToast({
                title: '没有更多订单了',
                icon: 'none',
                duration: 2000
            });
            return false;
        }
    },
    switchTab: function (event) {
        let showType = event.currentTarget.dataset.index;
        this.setData({
            orderList: [],
            showType: showType,
            pageNum: 0,
            pageSize: 10,
            totalPages: 0
        });
        this.getOrderList();
    },
    onReady: function () {
        // 页面渲染完成
    },
    onShow: function () {
        // 页面显示
        this.getOrderList();
    },
    onHide: function () {
        // 页面隐藏
    },
    onUnload: function () {
        // 页面关闭
    }
})