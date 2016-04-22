/**
 * Created by joker on 16-3-14.
 */
$(function () {
    //绑定用户注册事件
    $("#userRegisterModel").on("submit", function (e) {
        validateForm("register");
    });
    //绑定用户的注册事件
    $("#userLoginModel").on("submit",function(e){
       return validateForm("login");
    });
    //处理注册和验证

    function validateForm(model) {
        var result=false;
        if (model == "register") {
            var $registerUsername = $("#register_user_name").val();
            if ($.trim($registerUsername) == "") {
                alert("用户名不能为空！");
            } else {
                var $registerPassword = $("#register_user_psw").val();
                if ($.trim($registerPassword) == "") {
                    alert("用户密码不能为空！");
                } else {
                    var $registerConfirmPsw = $("#register_user_confirmpsw").val();
                    if ($.trim($registerConfirmPsw) == "") {
                        alert("确认密码不能为空！")
                    }  else {
                        var modal = UIkit.modal("#userRegisterDialog");
                        modal.hide();
                        result=true;
                    }
                }
            }
        } else if (model == "login") {
            var $loginUsername=$("#login_user_name").val();
            if($.trim($loginUsername)==""){
                alert("用户名不能为空！");
            }else{
                var $loginPassword=$("#login_user_psw").val();
                if($.trim($loginPassword)==""){
                    alert("密码不能为空！");
                }else{
                    result=true;
                }
            }

        }
        return result;
    }

});