var util = require('../../utils/util.js');
var api = require('../../config/api.js');
var app = getApp()
Page({
  data: {
    topicList: [],
    pageNum: 1,
    pageSize: 10,
    totalCount: 0,
    scrollTop: 0,
    showPage: false
  },
  onLoad: function(options) {
    // 页面初始化 options为页面跳转所带来的参数
    this.getTopic();
  },
  onReady: function() {
    // 页面渲染完成
  },
  onShow: function() {
    // 页面显示
  },
  onHide: function() {
    // 页面隐藏
  },
  onUnload: function() {
    // 页面关闭
  },
  nextPage: function(event) {
    var that = this;
    if (this.data.pageNum > that.data.totalCount / that.data.pageSize) {
      return true;
    }


    that.setData({
      pageNum: that.data.pageNum + 1
    });

    this.getTopic();

  },
  getTopic: function() {

    let that = this;
    that.setData({
      scrollTop: 0,
      showPage: false,
      topicList: []
    });
    // 页面渲染完成
    wx.showToast({
      title: '加载中...',
      icon: 'loading',
      duration: 2000
    });

    util.request(api.TopicList, {
      pageNum: that.data.pageNum,
      pageSize: that.data.pageSize
    }).then(function(res) {
      if (res.statusCode === 10000) {

        that.setData({
          scrollTop: 0,
          topicList: res.data.list,
          showPage: true,
          totalCount: res.data.totalCount
        });
      }
      wx.hideToast();
    });

  },
  prevPage: function(event) {
    if (this.data.pageNum <= 1) {
      return false;
    }

    var that = this;
    that.setData({
      pageNum: that.data.pageNum - 1
    });
    this.getTopic();
  }
})