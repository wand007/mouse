var util = require('../../utils/util.js');
var api = require('../../config/api.js');
var app = getApp()

Page({

    /**
     * 页面的初始数据
     */
    data: {
        issueList: [],
        showPage: false,
        pageNum: 0,
        pageSize: 10,
        totalPageCount: 0
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getIssue();
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {

    },

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {

    },

    /**
     * 生命周期函数--监听页面隐藏
     */
    onHide: function () {

    },

    /**
     * 生命周期函数--监听页面卸载
     */
    onUnload: function () {

    },

    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {

    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {

    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },
    nextPage: function (event) {
        var that = this;
        if (this.data.pageNum >= that.data.totalPageCount - 1) {
            return true;
        }

        that.setData({
            pageNum: that.data.pageNum + 1
        });

        this.getIssue();

    },
    getIssue: function () {

        let that = this;
        that.setData({
            showPage: false,
            issueList: []
        });

        util.request(api.IssueList, {
            pageNum: that.data.pageNum,
            pageSize: that.data.pageSize
        }).then(function (res) {
            if (res.statusCode === 10000) {

                that.setData({
                    issueList: res.data.list,
                    showPage: true,
                    totalPageCount: res.data.totalPageCount
                });
            }
        });

    },
    prevPage: function (event) {
        if (this.data.pageNum <= 1) {
            return false;
        }

        var that = this;
        that.setData({
            pageNum: that.data.pageNum - 1
        });
        this.getIssue();
    }
})