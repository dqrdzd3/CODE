// JavaScript Document
$(function(){
    var page = 1;
    var i = 2; //ÿ���4��ͼƬ
    //��� ��ť
    $("span.next").click(function(){    //��click�¼�
	     var $parent = $(this).parents("div.v_show");//���ݵ�ǰ���Ԫ�ػ�ȡ����Ԫ��
		 var $v_show = $parent.find("div.v_content_list"); //�ҵ�����Ƶ����չʾ����
		 var $v_content = $parent.find("div.v_content"); //�ҵ�����Ƶ����չʾ������Χ��DIVԪ��
		 var v_width = $v_content.width() ;
		 var len = $v_show.find("li").length;
		 var page_count = Math.ceil(len / i) ;   //ֻҪ����������������ķ���ȡ��С������
		 if( page == page_count ){  //�Ѿ������һ��������,�������󣬱�����ת����һ�����档
				$v_show.animate({ left : '0px'}, "slow"); //ͨ���ı�leftֵ����ת����һ������
				page = 1;
			}else{
				$v_show.animate({ left : '-='+v_width }, "slow");  //ͨ���ı�leftֵ���ﵽÿ�λ�һ������
				page++;
		 }
   });
    //��ǰ ��ť
    $("span.prev").click(function(){
	     var $parent = $(this).parents("div.v_show");//���ݵ�ǰ���Ԫ�ػ�ȡ����Ԫ��
		 var $v_show = $parent.find("div.v_content_list"); //Ѱ�ҵ�����Ƶ����չʾ����
		 var $v_content = $parent.find("div.v_content"); //Ѱ�ҵ�����Ƶ����չʾ����
		 var v_width = $v_content.width();
		 var len = $v_show.find("li").length;
		 var page_count = Math.ceil(len / i) ;   //ֻҪ����������������ķ���ȡ��С������
		 if( page == 1 ){  //�Ѿ�����һ��������,�������ǰ��������ת�����һ�����档
				$v_show.animate({ left : '-='+v_width*(page_count-1) }, "slow");
				page = page_count;
		}else{
				$v_show.animate({ left : '+='+v_width }, "slow");
				page--;
		}
    });

});

