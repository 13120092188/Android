# Android
移动应用开发技术课程作业

# Homework 1
Android 应用界面UI设计，包括一个登录界面和一个注册界面：
  
登录界面包括标题、用户头像、用户ID编辑框、用户密码编辑框、忘记密码、登录按钮、跳转到注册界面链接、第三方登录按钮。
分别用到的控件包括：
Layout：ConstraintLayout\LinearLayout
TextView
EditText
ImageView
Button
  
注册界面包括标题、手机号编辑框、发送验证码按钮、验证码编辑框、密码编辑框、注册按钮、第三方登录按钮。
分别用到的控件包括：
ConstraintLayout
TextView
EditText
Button


# Homework 2
实现了界面之间的跳转，加入了服务器实现了登录注册功能


# Homework 3
更加丰富了Gymclub应用的功能，运用了VideoView实现了视频的播放。
使用了android.location框架来实现了定位功能：
关键组件：
LocationManager
这个组件并不需要实例化，通常是通过函数 
getSystemService(Context.LOCATION_SERVICE) 
获得，该函数会返回一个LocationManager的实例，例如：
LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
LocationListener
通过回调（callback）来获取用户定位信息，需要通过新建一个 LocationListener 类，并实现其中的几个回调方法
 
Location
这是一个数据类，保存了定位的信息。


# Homework 4
实现离线缓存功能
核心内容：
申请sd卡权限
既然要进行离线缓存就需要往手机本地存储写入内容，所以首先需要申请获得手机的“存储权限”然后才可以写入手机本地内容。
创建数据类，比如保存用户数据，创建Person类
 
编写保存object对象到文件，和读取缓存


# Homework 5
第三方登录功能的实现（或者说尝试。。）
以QQ登录为例
首先需要到腾讯开放平台进行应用接入的注册
选择Android平台就会获取APPID和APPKEY创建完成后我们需要到下载SDKJar包，将jar包添加到我们的项目中去
在Androidmanifest 在里面加入权限和注册Activity 
 

