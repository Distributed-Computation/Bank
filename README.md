# Bank

### 改造课堂所介绍的rpc框架程序，利用该程序，采用rpc模式开发一个银行atm模拟系统。<br>
### 系统主要提供一个服务Card，该服务接口可以提供登录、查询、取钱、存钱等功能。服务接口的设计和实现自定义。<br>

#### Atm客户端功能需求:<br>
**1、ATM可以实现用户登录、查询、取钱、存钱操作。所有操作以rpc的方式透明地调用服务器端的远程服务对象完成。客户端不能直接访问数据库系统。**<br>
2、用户输入银行账户和密码，可以登录，若登录失败则提示不能登录。**<br>
**3、登录后可以显示主菜单菜单分别为查询余额、取钱、存钱和退出。**<br>
**4、从3的界面中选择查询，则显示账户余额，并可以退回到主菜单界面重新显示主菜单。**<br>
**5、从3的界面中选择取钱，则输入取钱金额。若取钱金额超过余额，则提示错误，否则提示取钱成功，并显示余额，同样本界面可以退回到主菜单界面重新显示主菜单。**<br>
**6、从3的界面中选择存钱，则输入存钱金额，然后提示取钱成功，并显示余额，同样本界面可以退回到主菜单界面重新显示主菜单。**<br>
**7、从3的界面中选择退出则退出系统。**<br>

#### 服务器端功能需求:<br>
1、银行卡数据保存在数据库中，请自行设计数据库的相关结构。**<br>
**2、可以通过rpc的方式接受客户端的调用完成相关的数据存取。**<br>

#### rpc框架功能需求：
1、采用多线程功能，使得该rpc框架可以接受任意多次并发调用。<br>
**2、改造rpc框架，为rpc增加一个可配置的rpc服务可用性扩展，通过配置文件可以至少配置两台以上的服务器同时提供服务端的调用，例如：在配置文件中可以配置两台服务器的地址和端口。客户端无须指定rpc调用的服务器地址和端口，可以简单使用负载均衡随机或轮询算法的方式选择一台服务器进行rpc调用。整个rpc负载均衡调用的过程对于客户端的程序应该完全透明。<br>
（提示：在本地一台机器调试的时候，可以通过简单运行两个服务端程序，但是服务打开的端口不同的方式来模拟两台服务器）**<br>
**4、当一台RPC远程调用网络连接失败，可以自动转向另一台服务器进行调用，当两台服务器均不能联通则报一个错误。**<br>
**5、为rpc服务器端调用增加一个简单的可配置的AOP，使得程序开发人员今后可以方便的对银行卡服务的一个或多个功能（如登录、取钱等）进行切面编程。**<br>
6、以AOP的方式，做扩展如下：<br>
在登录功能后执行一个检查同一个账户是否连续失败次数是否超过3次，如超过3次，则锁定账户，该账户今后将不能再次登录。<br>
**在取钱操作后，执行一个登录日志功能，将取钱账户、取钱金额、取钱日期、取钱前/后账户余额写入数据库日志。**<br>
**7、为了简化项目开发，可以假设本rpc服务端上固定只运行一个Card服务，且无须考虑注册中心的功能。**<br>

