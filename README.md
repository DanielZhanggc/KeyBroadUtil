# KeyBroadUtil  
用于解决键盘弹出收起各种布局问题  
# 使用方法  
1.将KeyBoardUtil/PanHideUtil/PanHideUtil复制到自己的工程之中  

2.监听键盘弹出和收起  
`<hello world>`
KeyBoardUtil.getInstance().setOnKeyBoardPopListener(this, new KeyBoardUtil.OnKeyBoardPopListener() {
            @Override
            public void keyBoardShow(int i) {
                Toast.makeText(MainActivity.this, "键盘弹出", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void keyBoardHide(int i) {
                Toast.makeText(MainActivity.this, "键盘收起", Toast.LENGTH_SHORT).show();
            }
        });
        
3.解决沉浸式样式下布局被键盘遮蔽的问题  
PanHideUtil.getInstance().attachActivity(this);
注意：这句代码一定要在指定布局后添加，否则会包空指针异常

4.LockableScrollView主要为了解决键盘在弹出时压缩上面布局的问题，如果使用ScrollView则上面布局在键盘弹出后可滑动  
使用LockableScrollView可在代码里控制上面布局是否可滚动
# 原理  
其实原理很简单，在弹出键盘的时候布局会发生变化，onGlobalLayout就能够监听到布局的变化，我们可以利用布局特点来针对性的解决
一些问题

