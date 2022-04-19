/*
JavaScript Document
ckplayer5.3,有问题请访问http://www.ckplayer.com，感谢软件作者的辛勤劳动。
Fufu_网易皮肤分享，品味经典 http://www.fufuok.com/。
免费软件，请保持本版权信息
*/
function ckstyle() {//定义总的风格
    var ck = new Object();
    ck.cpath = 'mini/'; //风格总路径
    ck.mylogo = 'mylogo.png';//开始加载的logo,如果为空就不加载
    ck.logo = '"",3,65,26';//logo图片，对齐方式，0左上1左下2右上3右下，x偏移量，y偏移量
    ck.buffer = 'buffer.swf';//缓冲图标地址，推荐使用swf
    ck.controlbar = 'buttom_bg.png';
    ck.cplay = 'play_out.png,play_on.png';
    ck.cpause = 'pause_out.png,pause_on.png';
    ck.pausec = 'pause_scgedyke.png,pause_scgedyke_on.png';
    ck.sound = 'sound_out.png,sound_on.png';
    ck.mute = 'mute_out.png,mute_on.png';
    ck.full = 'full_out.png,full_on.png';
    ck.general = 'general_out.png,general_on.png';
    ck.cvolume = 'volume_back.png,volume_on.png,schedule.png';
    ck.schedule = 'schedule_bg.png,schedule_load.png,schedule_play.png,schedule.png';
    ck.fast = 'fashf_out.png,fashf_on.png,fashr_out.png,fashr_on.png';
    ck.advmute = 'v_off.png,v_on.png';
    ck.advjump = 'adv_skip.png';
    ck.advclose = 'close_adv.png';
    ck.control_r = '"",2,1,-75,-100';
    ck.control_c = '"",1,1,-165,-90';
    ck.control_c2 = '"",1,1,-165,-90';
    ck.control_rel = 'related.swf,related.mini.xml?id=[$pat],0';//视频播放结束后分享和推荐视频
    //右侧调整按钮的文件，坐标
    ck.setup = '1,1,1,1,1,1';
    //总体设置：是否使用鼠标手型1是使用手型0是不使用，是否支持单击暂停1是支持0不支持，是否支持双击全屏1是支持0不支持,在播放前置flash广告时是否同时加载视频,logo在播放广告时是否显示1不显示0显示,是否采用http视频直播流0不是1采用
    ck.pm_repc = '[]->&';
    //视频地址替换符，当视频地址里包含&这样的符号时请用上面的符号
    ck.pm_spac = '|';
    //视频地址间隔符，默认使用逗号，如果视频地址里本身存在逗号的话需要另外设置一个间隔符，注意，即使只有一个视频也需要设置
    ck.pm_ctbar = '1,2,10,-10,10,5,0,2,3000';
    //控制栏的参数，这里分二种情况,第7个参数是设置定位方式(0：相对定位，1：绝对定位)
    //0：第一种情况的参数说明，默认1:中间对齐，上中下对齐，离左边的距离，Y轴偏移量，离右边的距离，高度，定位方式，隐藏方式(0不隐藏，1全屏时隐藏，2都隐藏)，隐藏时间
    //1：第二种情况的参数说明，左中右对齐，上中下对齐，x偏移量，y偏移，宽度，高度，定位方式，隐藏方式(0不隐藏，1全屏时隐藏，2都隐藏)，隐藏时间
    ck.pm_sch = '1,2,18,-10,62,5,0,3,5';
    //进度条的参数，这里分二种情况，前面7个参数对照控制栏的，第8个参数是拖动按钮的宽，第9个参数是拖动按钮的高
    ck.pm_play = '0,2,10,-10';
    //播放和暂停按钮的坐标
    ck.pm_clock = '0,2,15,-10,0';
    //已播放时间的坐标和宽度
    ck.pm_clock2 = '0,2,15,-10,0';
    //总时间的坐标和宽度
    ck.pm_full = '2,2,-15,-10';
    //全屏和取消全屏按钮的坐标
    ck.pm_vol = '2,2,-50,-10,30,5,3,5';
    //音量调节框的坐标，宽度，高度，拖动按钮的宽度,高度
    ck.pm_sound = '2,2,-57,-10';
    //静音和取消静音的坐标
    ck.pm_fastf = '2,2,-5,-10';
    //快进按钮的坐标
    ck.pm_fastr = '0,2,0,-10';
    //快退按钮的坐标
    ck.pm_fasttime = '10';
    //快进和快退的秒数
    ck.pm_video_bottom = '0,0';
    //视频离底部的距离，广告的底部参考距离-该参数的作用请到网站查看
    ck.pm_video_bo = '200,1';
    //视频缓冲时间-默认200毫秒,是否优化视频-默认为1优化
    ck.pm_pa = '0,30';
    //是否自动调整中间暂停按钮的位置-默认0自动调整1是不自动调整，中间暂停按钮的透明度
    ck.pm_buffer_wh = '30,30';
    //缓冲图片的宽和度
    ck.pm_pr = '0x000000,0xfdfdfd,0xffffff,4,30,80,18,5';
    //提示框背景颜色,边框颜色,文字的颜色,边框的弧度,提示框透明度,文字透明度,提示框的高度,离按钮的距离
    ck.pm_advmaskap = '100';
    //播放前置广告时底部透明度0-100,只对flash有效，视频时将自动隐藏控制栏，图片时将自动设置成0，做为链接用
    ck.pm_advstatus = '1,3,88,30';
    //前置广告是否显示静音按钮(0/1),位置(0/1/2/3),x,y
    ck.pm_advjp = '1,0,3,88,58';
    //前置广告是否显示跳过广告按钮,跳过按钮触发对象(值0/1,0是直接跳转,1是触发js:function ckadjump(){}),对齐方式,x,y
    ck.pm_advtime = '95,2,100,5';
    //前置广告倒计时文本宽,对齐方式,x,y
    ck.pm_advmarquee = '0,1,2,5,-30,20,20,0,0,15,3000,1,20,0x000000';
    //滚动广告的参数，这里分二种情况,第8个参数是设置定位方式(0：相对定位，1：绝对定位)
    //0：第一种情况的参数说明，是否显示，默认1:中间对齐，上中下对齐，离左边的距离，Y轴偏移量，离右边的距离，高度，定位方式，滚动方向（0向左1向上），滚动行距，移动单位像素，移动单位时长，向上滚动时每次停留时间，背景颜色
    //1：第二种情况的参数说明，左中右对齐，上中下对齐，x偏移量，y偏移，宽度，高度，定位方式，滚动方向（0向左1向上），向上时的高度，滚动行距，移动单位像素，移动单位时长，向上滚动时每次停留时间，背景颜色
    //是否显示底部广告(0/1),背景颜色,高度,左边的距离,右边的距离,离下面的距离,滚动的距离(0静止),间隙时间(越小越快,建议不小于20),如果开启广告需要有function ckmarqueeadv(){return '广告内容'}
    ck.advmarquee = '{font color="#ffffff"}区分一下，{font color="#fcd527"}这里是JS风格中设计的滚动字幕{/font}：感谢各位关心、支持谭谈交通节目的朋友位，有了你们的支持和关注，才有谭谈交通网持续的发展，我们将尽心尽力为大家奉上最新的节目视频，请大家支持 {a href="http://www.08z.com/" target="_blank"}{font color="#fcd527"}谭谈交通网 www.08z.com{/font}{/a}，支持谭乔警官：{a href="http://weibo.com/u/1805524974" target="_blank"}新浪微博{/a} {a href="http://t.qq.com/cd_tanqiao" target="_blank"}腾讯微博{/a}！ {/font}';
    ck.pm_advms = '1,3,15,26';
    //滚动广告是否显示关闭按钮(0/1),位置(0/1/2/3),x,y
    ck.pm_load = '100,20,0,40,{font color="#fdfdfd" face="arial"}　　已加载 [$prtage]%{/font},{font color="#fcd527" face="arial"}加载失败，请刷新{/font}';
    //加载视频百分比的位置默认距中，参数说明：宽度,高度,右偏移（正/负），下偏移(正/负),字符[$prtage]将被自动替换成百分比的数字,加载失败的提示
    ck.pm_buffer = '25,20,0,22,{font color="#fdfdfd" face="arial"}[$buffer]%{/font}';
    //视频缓冲百分比的位置默认距中，参数说明：宽度,高度,右偏移（正/负），下偏移(正/负),只显示百分比数据
    ck.pm_statustrue = '3';
    ck.pr_play = '点击播放';
    ck.pr_pause = '点击暂停';
    ck.pr_mute = '点击静音';
    ck.pr_nomute = '取消静音';
    ck.pr_full = '点击全屏';
    ck.pr_nofull = '退出全屏';
    ck.pr_fastf = '快进';
    ck.pr_fastr = '快退';
    ck.pr_time = '[$Time]';
    //[$Time]会自动替换目前进度提示
    ck.pr_volume = '[$Volume]';
    //[$Volume]会自动替换音量
    ck.pr_clock = '';
    //{font color="#fdfdfd" size="12" face="arial"}[$Time]{/font}这里定义进度时间的显示，并且同时会替换二个参数，[$Time]会被替换成已播放时间，[$TimeAll]会被替换成总时间
    ck.pr_clock2 = '';
    //{font color="#fdfdfd" size="12" face="arial"}~ [$TimeAll]{/font}同pr_clock,这二个是相等的，主要是为了方便在不同的地方调用已播放时间和总时间
    ck.pr_adv = '{font color="#FFFFFF" size="12" face="arial"}广告还有：{font color="#dee309"}{b}[$Second]{/b}{/font} 秒{/font}';
    ck.myweb = 'http://www.fufuok.com/flv-player-ckplayer.html,fufuok';
    ck.cpt_list = ckcpt();
    return ck;
}

function ckcpt() { //自定义加载图片或flash，该类可以自己随意添加多个自定义的图片或者flash,请注意前面的都要在最后一个参数后面加上竖号，做为区隔，最后一个不用加
    var cpt = "";
    cpt += 'sch_lr.png,0,2,14,-36|';
    //分别是图片或flash地址，水平对齐方式，垂直对齐方式，水平偏移量，垂直偏移量
    cpt += 'sch_lr.png,2,2,-15,-36|';
    return cpt;
}
