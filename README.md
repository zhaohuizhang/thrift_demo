#About thrift
thrift是一种可伸缩的跨语言服务的发展软件框架。它结合了功能强大的软件堆栈的代码生成引擎，以建设服务，工作效率和无缝地与C + +，C#，Java，Python和PHP和Ruby结合。thrift是facebook开发的，我们现在把它作为开源软件使用。thrift允许你定义一个简单的定义文件中的数据类型和服务接口。以作为输入文件，编译器生成代码用来方便地生成RPC客户端和服务器通信的无缝跨编程语言（来自百度百科）。    
  >>>最初由facebook开发用做系统内个语言之间的RPC通信 。 
  >>>2007年由facebook贡献到apache基金 ，现在是apache下的opensource之一 。 
  >>>支持多种语言之间的RPC方式的通信：php语言client可以构造一个对象，调用相应的服务方法来调用java语言的服务 ，跨越语言的C/S   rpc  调用 。 
  
#Thrift 协议栈 以及各层的使用（java 为例） 
 
##model   interface 
       服务的调用接口以及接口参数model、返回值model 
##Tprotocol    协议层 
         将数据（model）编码 、解码 。 
##Ttramsport 传输层 
        编码后的数据传输（简单socket、http） 
##Tserver 
        服务的Tserver类型，实现了几种rpc调用（单线程、多线程、非阻塞IO） 
        
#Run
##Run Server
##Run Client
