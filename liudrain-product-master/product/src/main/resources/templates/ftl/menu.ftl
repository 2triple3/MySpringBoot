<div class="container">
    <div class="row">
        <div class="col-md-12">

            <nav class="navbar brb" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-reorder"></span>
                    </button>
                    <a class="navbar-brand" href="index.html"><#--<img src="../static/img/logo.png"/>--></a>
                </div>
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                                        class="icon-camera"></span>内容管理</a>
                            <ul class="dropdown-menu">
                                <li><a href="/content/image">公共图片库</a></li>
                                <li><a href="/content/contentList">内容列表</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon-hdd"></span>文章管理</a>
                            <ul class="dropdown-menu">
                                <li><a href="/article/list?type=1">免费文章</a></li>

                                <li><a href="/article/list?type=2">付费文章</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon-hdd"></span>订单数据管理</a>
                            <ul class="dropdown-menu">
                                <li><a href="/order/price">价格管理</a></li>
                                <li><a href="/order/list">订单管理</a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon-apple"></span>产品管理</a>
                            <ul class="dropdown-menu">
                                <li><a href="/category/list">类别列表</a></li>
                        <#--        <li><a href="#">基础数据<i class="icon-angle-right pull-right"></i></a>
                                    <ul class="dropdown-submenu">
                                        <li><a href="/attributeclass/list">类别电气参数维护</a></li>
                                        <li><a href="/decisionclass/list">类别决策参数维护</a></li>
                                    </ul>
                                </li>-->
                                <li><a href="/product/list">产品列表</a></li>
                            </ul>
                        </li>

                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span
                                        class="icon-bitbucket"></span>用户数据管理</a>
                            <ul class="dropdown-menu">
                                <li><a href="/admin/permission">用户管理</a></li>
                            </ul>
                        </li>

                    </ul>


                    <div class="form-group navbar-form navbar-right">

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="icon-bitbucket"></span>
                            welcome，${adminUser.adminName}</a>
                        <ul class="dropdown-menu">
                            <li><a href="/admin/logout">注销</a></li>
                            <li><a href="/admin/editpwd">密码修改</a></li>

                        </ul>

                    </div>

                </div>

            </nav>

        </div>

    </div>


</div>