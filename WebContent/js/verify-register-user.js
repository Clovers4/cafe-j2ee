// 成功函数
function success(obj, msg) {
	obj.removeClass('has-error').addClass('has-success');
	obj.find('.tips').hide();
	obj.find('.glyphicon-ok').show();
	obj.find('.glyphicon-remove').hide();
}

// 失败函数
function fail(obj, msg) {
	obj.removeClass('has-success').addClass('has-error');
	obj.find('.glyphicon-remove').show();
	obj.find('.glyphicon-ok').hide();
	obj.find('.tips').text(msg).show();
}

// 用户名
var regAccount = /^[a-zA-Z_][a-zA-Z0-9_]{4,19}$/;
var checkRegisterAccount = false;
// 用户名匹配
$(document).ready(function() {
	$('#register-account-div').find('#register-account').change(function() {
		if (regAccount.test($(this).val())) {
			success($("#register-account-div"));
			checkRegisterAccount = true;
		} else if ($(this).val().length < 5) {
			fail($("#register-account-div"), '用户名太短，不能少于5个字符');
			checkRegisterAccount = false;
		} else {
			fail($("#register-account-div"), '用户名只能为英文数字和下划线,且不能以数字开头')
			checkRegisterAccount = false;
		}

	});
});

// 密码
var regPasswordSpecial = /[~!@#%&=;':",./<>_\}\]\-\$\(\)\*\+\.\[\?\\\^\{\|]/;
var regPasswordAlpha = /[a-zA-Z]/;
var regPasswordNum = /[0-9]/;
var registerPassword;

var checkRegisterPassword = false;
var checkRegisterPasswordConfirm = false;

// 密码匹配
// 匹配字母、数字、特殊字符至少两种的函数
function atLeastTwo(password) {
	var a = regPasswordSpecial.test(password) ? 1 : 0;
	var b = regPasswordAlpha.test(password) ? 1 : 0;
	var c = regPasswordNum.test(password) ? 1 : 0;
	return a + b + c;
}

// 注册密码验证
$(document).ready(function() {
	// 检验长短以及是否包含多种特殊字符
	$('#register-password-div').find('#register-password').change(function() {
		registerPassword = $(this).val();
		if (registerPassword.length < 8) {
			fail($("#register-password-div"), '密码太短，不能少于8个字符');
			checkRegisterPassword = false;
		} else {
			if (atLeastTwo(registerPassword) < 2) {
				fail($("#register-password-div"), '密码中至少包含字母、数字、特殊字符的两种');
				checkRegisterPassword = false;
			} else {
				success($("#register-password-div"));
				checkRegisterPassword = true;
			}
		}
	});

});

// 注册再次输入密码校验

$(document)
		.ready(
				function() {
					$('#register-passwordConfirm-div')
							.find('#register-passwordConfirm')
							.change(
									function() {
										if ($(this).val() == registerPassword) {
											success($("#register-passwordConfirm-div"));
											checkRegisterPasswordConfirm = true;
										} else {
											fail(
													$("#register-passwordConfirm-div"),
													'两次输入的密码不一致');
											checkRegisterPasswordConfirm = false;
										}
									});
				});

// 手机号码
var regTel = /^[0-9]{11}$/;
var checkRegisterTel = false;
$(document).ready(function() {
	$('#register-tel-div').find("#register-tel").change(function() {
		if (regTel.test($(this).val())) {
			success($("#register-tel-div"));
			checkRegisterTel = true;
		} else {
			fail($("#register-tel-div"), '手机号码只能为11位数字');
			checkRegisterTel = false;
		}
	});
});

// 邮箱
var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
var checkRegisterEmail = false;
$(document).ready(function() {
	$('#register-email-div').find("#register-email").change(function() {
		if (regEmail.test($(this).val())) {
			success($("#register-email-div"));
			checkRegisterEmail = true;
		} else {
			fail($("#register-email-div"), '邮箱格式错误,正确格式如:123312@qq.com');
			checkRegisterEmail = false;
		}
	});

});

// 注册提交检验
function checkRegister() {
	if (checkRegisterAccount == true) {
		success($("#register-account-div"));
	} else {
		fail($("#register-account-div"));
	}

	if (checkRegisterPassword == true) {
		success($("#register-password-div"));
	} else {
		fail($("#register-password-div"));
	}

	if (checkRegisterPasswordConfirm == true) {
		success($("#register-passwordConfirm-div"));
	} else {
		fail($("#register-passwordConfirm-div"));
	}

	if (checkRegisterTel == true) {
		success($("#register-tel-div"));
	} else {
		fail($("#register-tel-div"));
	}

	if (checkRegisterEmail == true) {
		success($("#register-email-div"));
	} else {
		fail($("#register-email-div"));
	}

	if (checkIdcode == true) {
		success($("#idcode-btn-div"));
	} else {
		fail($("#idcode-btn-div"));
	}

	if (checkRegisterAccount == true && checkRegisterPassword == true
			&& checkRegisterPasswordConfirm == true && checkRegisterTel == true
			&& checkRegisterEmail == true && checkIdcode == true) {
		return true;
	} else {
		event.preventDefault();
		return false;
	}
};

