jQuery(document).ready(function() {

	var dayIntros = new Array();
	dayIntros[1] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;从伦敦飞往芬兰首都赫尔辛基，踏上北欧的土地，开始这段神奇的拉普兰大陆探险之旅。拥有大面积水域的海港城市赫尔辛基优雅地融入波罗的海，这与它一国之都的身份非常相称。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;赫尔辛基的迷人很大程度上在于它的古老，简朴但辉煌的新艺术主义建筑，宽敞雅致的百年咖啡店，许多博物馆里保存完好的芬兰遗产，20世纪30年代以来就从未改变菜单和家具陈设的餐馆，这一切都是这座城市醉人魅力的一部分。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在赫尔辛基海港旁边的露天自由市场逛一逛夜市吧，您会发现自己会喜欢上赫尔辛基的。";
	dayIntros[2] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;从今天开始，我们将收拾行囊一路向北，向隐藏在北极圈内的拉普兰大陆出发。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话说芬兰号称“千湖之国”，如果没有去芬兰南部的湖区国家公园，又怎么能叫去过芬兰？湖区国家公园的水域面积比陆地还多，那里的水圣洁干净，闪烁着光芒，像镜子一般倒映着蓝天和森林。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;一路向北，一路美景，美不胜收。";
	dayIntros[3] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;第三天游览的重点是卢奥。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;生机勃勃的卢奥绵延数座岛屿，岛屿之间由优雅的步行桥连接，无论你身在何处，都离水不远。河畔的露天自由市场是芬兰最热闹最彩色斑斓的集市之一，这里有红色木结构的仓库（现在是餐馆、酒吧和工艺品商店）、各色各样的杂货摊，并矗立着以幽默方式表现当地警察的圆形Toripoliisi雕像。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;晚上抵达海港城市凯米，在那里我们已经能够依稀感受到来自拉普兰的气息了。";
	dayIntros[4] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;凯米是个重要的深海港口城市，虽然吸引人的地方不算多，但这里拥有迷人的粉红教堂，以及芬兰最令人震撼的冬季名胜——北极破冰船Sampo号。Sampo号是一艘真正的北极破冰船，也是世界上唯一一艘定期接待旅行者的破冰船，乘坐Sampo号破冰船去波的尼亚湾进行破冰之旅实在是一种刺激而奇妙的体验（提供破冰船官方证书）。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;长达4小时的破冰之旅，将会是您一生难以忘怀的回忆。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今天，就让我们迎风出海，远航破冰！哦，别忘了还有平安夜丰盛的圣诞大餐呢。";
	dayIntros[5] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;罗瓦涅米以北8公里的圣诞老人村，是全世界小朋友的童话之地。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在这里，圣诞老人邮局每年都会收到将近75万封全球各地的儿童寄来的信件！最有吸引力的还是圣诞老人本身，他会在这里会见来自世界各地的旅行者。北极圈分界碑也坐落在圣诞老人村，为什么不在北极圈分界碑面前做一个起跑姿势，向着北极圈和自己的梦想出发呢？哇塞，圣诞节在圣诞老人村度过，这是一件多么不可思议的事情！";
	dayIntros[6] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;冰旅馆Ice Hotel，很多人小时候就听说过这个神奇旅馆的存在。用当地冰封河流中成百上千冰块建成的冰旅馆，拥有50多个雕刻着精美冰雕的艺术房间，每一年每一个艺术房间都有各自不同的冰雕主题，睡公主、中国龙、银河系等等，配上五颜六色的彩色射灯，整个房间如梦如幻。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今夜，我们将会安排您入住大名鼎鼎的冰旅馆，感受一下世界上最刺激的睡眠！什么？室温-5℃太冷睡不着？别担心，冰床上厚厚的毛皮毯子以及温暖的睡袋是您最值得信赖的家伙了。";
	dayIntros[7] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被一杯热气腾腾的覆盆子汁唤醒后，在冰旅馆的餐厅享用一顿丰盛的早餐吧，以补充昨晚与冰床“搏斗”所失去的能量。随后参观冰旅馆的冰酒吧和冰教堂，听Ice Hotel工作人员为您讲述冰旅馆的来源以及建造过程，这将会令您大开眼界。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;取得冰旅馆的官方入住证书后，我们继续出发前往北部的阿比斯库。美丽的景色、友好热情的人们和历史悠久的萨米文化，这一切使阿比斯库成为所有旅行者游览拉普兰的必到之处。阿比斯库国家公园占地75平方公里，从南部海岸漂亮的Tornetrask湖一直延伸出来。周围都是Lapporten壮丽的景色，周围的山脉如同一扇大门，带您走进拉普兰的传奇世界。";
	dayIntros[8] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;昨夜在旷野看到了令人震撼的北极光？还是在为没有下决心洗一个瑞典传统的男女共浴裸体桑拿而懊恼？抛开它们吧，今天是与狗狗们一起并肩作战的日子！挑选属于您自己的哈士奇雪橇犬队伍，检查好雪橇和绳索，我们出发吧！<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;在茫茫的雪原上，驾驶着雪橇与同伴们一较高下；搂着狗狗们，来一张亲密的合照；围着篝火，再来一杯热咖啡暖暖身子。美妙的狗拉雪橇之旅，必定会给您留下一段难忘的回忆。";
	dayIntros[9] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今天我们与哈士奇狗狗们挥手告别，再一次跨过国界线返回芬兰，准备继续往更北的极地进发。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下一个目的地是欧洲最顶尖的滑雪胜地之一Levi，拥有45条灯光照明雪坡滑道，滑道总长高达230公里。这里是滑雪爱好者的天堂，是滑雪爱好者最向往的滑雪胜地之一。";
	dayIntros[10] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;或许您之前在其它地方的滑雪场滑过雪。但请相信我们，在Levi滑雪的感觉绝对是其它地方所不能媲美的！今天，我们将会为VIP独立团预约一位私人滑雪教练，手把手教您如何从滑雪菜鸟变成滑雪达人。如果您已经是滑雪高手？那就放胆来挑战一下这里的世界顶级野外滑雪赛道吧！";
	dayIntros[11] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;什么？白天在冰湖上开着雪地摩托90迈疾驰还不够过瘾？！不要紧，晚上还有更大的惊喜。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;很多人会在网上看到过玻璃圆顶小屋的照片，但同时也有很多人不清楚它到底在哪里。很多人会幻想着睡在玻璃圆顶小屋里面邂逅突如而至的北极光，但同时也有很多人只把它当做是一个遥不可及的梦想而不是目标。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;但今晚，我们将会入住这个梦幻一般的屋子！想象一下，和心爱的人一起躺在可以自由调节角度的床上，透过透明的圆顶玻璃房顶，看着天上的漫天银河，心里盼望着极光女神欧若拉的赴约，那种浪漫想必终生难忘。";
	dayIntros[12] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;今天将会是在北极圈内的最后一天，怎么能不去看看位于罗瓦涅米的极地博物馆呢？极地博物馆是芬兰最好的博物馆之一，有一条非常漂亮的玻璃隧道，一直延伸到Ounasjoki。这里有两个主要的展览：一边关于拉普兰，有一些关于萨米文化的讲述；而亮点则位于另一边，有大量关于北极本身的展示，这里的静态和交换式展示都很棒，主要是关于北极的动植物以及在北极生活的欧洲人、亚洲人和北美人。";
	dayIntros[13] = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;从罗瓦涅米乘坐返程航班，经赫尔辛基中转返回伦敦。在飞机上再一次俯视这片圣白晶莹的、有着自己灵魂的土地，挥手说再见。<br/><div class='intro-line-separator'></div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;关于拉普兰大陆的传说，其实这个传说的故事很简单，就是与这片土地说再见的人，一定会再见。";
	
	
	$(".travel-days li").mouseover(function(event){
		var idx = $(this).attr("idx");
		$(".travel-days li").removeClass("day-highlight");
		$(this).addClass("day-highlight");
		$(".intro-box .title img").attr("class", "title-" + idx);
		$(".intro-box .description .m-bg").html(dayIntros[idx]);
	});
	
	$(".sights-nav li").mouseover(function(event){
		var idx = $(this).attr("idx");		
		$(".sights-nav li div").removeClass("sn-highlight");
		$(this).find("div").addClass("sn-highlight");
		
		$(".sights").html("<div style='color: white; font-size: 14.5px; margin-left: 150px; margin-top: 200px; width: 500px; text-align: center; line-height: 25px;'>图片加载中......</div>");
		
		$(".sights").attr("style", "background-image: url('./img/lapland/sight-" + idx + ".png')");
		
		$(".sights div").remove();
		
		if(idx == 11) {
			$(".sights").html("<div class='discount-box'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;为了挑战大家的<em class='text-highlight'>人品极限</em>，海诺旅游承诺将会在<em class='text-highlight'>7位</em>VIP独立团客人当中抽出一位幸运儿，返还团费总价的<em class='text-highlight'>一半</em>！报名结束后，抽签活动将会在海诺旅游<em class='text-highlight'>诺丁汉总部办公室</em>进行，由客户本人或委托朋友亲自参与，确有特殊原因不能亲自参与者则通过Facetime等方式监督并委托工作人员代为参与。现场抽签共分为两轮，第一轮抽签决定第二轮抽签的顺序，第二轮抽签则决定幸运儿花落谁家。整个抽签过程将全程录像并在网络公布，确保公平公开公正。抽签结束后，海诺旅游会当场通过转账方式将<em class='text-highlight'>一半</em>金额退还至幸运儿银行账户。</div>");
		}
	});
	
});