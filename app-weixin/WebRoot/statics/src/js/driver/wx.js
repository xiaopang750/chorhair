// setTimeout(function(){
  wx.config({
      debug: false,
      appId: 'wx04bf3ce7725466d1',
      timestamp: timeUrl,
      nonceStr: 'Wm3WZYTPz0wzccnW',
      signature: signature,
      jsApiList: [
        'openLocation'
      ]
  });

  wx.ready(function () {
    document.querySelector('#openLocation').onclick = function () {
      wx.openLocation({
          latitude: 40.00462,
          longitude: 116.480143,
          name: '虫二望京一店',
          address: '北京市朝阳区望京西园三区西门302楼底商',
          scale: 14
          //infoUrl: 'http://weixin.qq.com'
      });
    };
  });

  wx.error(function (res) {
    alert(res.errMsg);
  });
// },2000);


