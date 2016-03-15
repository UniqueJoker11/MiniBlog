/**
 * Created by joker on 16-3-14.
 */
$(function () {
    //绑定用户注册事件
    $("#userRegisterBtn").on("click", function (e) {
        validateForm("register");
    });
    //处理注册和验证
    function validateForm(modal) {
        if (modal == "register") {
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
                        var person = new Object();
                        person.username = $("#register_user_name").val();
                        person.password = $("#register_user_psw").val();
                        person.confirmPasswrod = $("#register_user_confirmpsw").val();
                        $.ajax({
                            type: "post",
                            url: "/miniblog/user/register",
                            data: person,
                            dataType: "json",
                            success: function (data) {
                                //如果返回true，那么关闭对话框以及输出用户名
                                if(data.success){
                                    var modal = UIkit.modal("#userRegisterDialog");
                                    modal.hide();
                                    $("#loginModule").html(
                                            "你好"+person.username+",欢迎回来"
                                    );
                                } else{
                                    alert(data.msg);
                                    $("#userRegisterDialog :input").val("");

                                }


                            }

                        });
                    }
                }
            }
        } else if (modal == "login") {

        }
    }

});