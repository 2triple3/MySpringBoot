<!DOCTYPE html>
<html>
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <title>善问产品选择系统</title>

    <link href="/static/css/stylesheets.css" rel="stylesheet" type="text/css"/>
    <script type='text/javascript' src='/static/js/wangEditor.js'></script>
    <script type='text/javascript' src='/static/js/wangEditor.min.js'></script>
    <script type='text/javascript' src='/static/js/wangEditor.js'></script>
    <script type='text/javascript' src='/static/js/plugins/jquery/jquery.min.js'></script>
    <script type='text/javascript' src='/static/js/plugins/jquery/jquery-ui.min.js'></script>
    <script type='text/javascript' src='/static/js/plugins/jquery/jquery-migrate.min.js'></script>
    <script type='text/javascript' src='/static/js/plugins/jquery/globalize.js'></script>
    <script type='text/javascript' src='/static/js/plugins/bootstrap/bootstrap.min.js'></script>
    <script type='text/javascript' src='/static/js/plugins/flot/jquery.flot.js'></script>
    <script type='text/javascript' src='/static/js/plugins/flot/jquery.flot.resize.js'></script>
    <script type='text/javascript' src='/static/js/plugins/mcustomscrollbar/jquery.mCustomScrollbar.min.js'></script>
    <script type='text/javascript' src='/static/js/plugins/mcustomscrollbar/jquery.mousewheel.min.js'></script>
    <script type='text/javascript' src='/static/js/plugins/uniform/jquery.uniform.min.js'></script>

    <script type='text/javascript' src='/static/js/plugins/knob/jquery.knob.js'></script>
    <script type='text/javascript' src='/static/js/plugins/sparkline/jquery.sparkline.min.js'></script>

    <script type='text/javascript' src='/static/js/plugins/fullcalendar/fullcalendar.min.js'></script>

    <script type='text/javascript' src='/static/zTree/jquery.ztree.core.js'></script>

    <link href="../static/zTree/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css"/>

    <script type='text/javascript' src='/static/js/plugins.js'></script>
    <script type='text/javascript' src='/static/js/actions.js'></script>
    <script type='text/javascript' src='/static/js/charts.js'></script>
    <script type='text/javascript' src='/static/js/settings.js'></script>
    <script type='text/javascript'
            src='/static/js/jquery.twbsPagination.js'></script>
    <script>
        function onclickfclose(id) {
            var ul = document.getElementById(id);
            var objv = ul.style.display;
            if (objv == 'none') {
                ul.style.display = 'block'
            } else {
                ul.style.display = 'none';
            }
        }
    </script>
    <#import "/ftl/spring.ftl" as spring>


</head>
