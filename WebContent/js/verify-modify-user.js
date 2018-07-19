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


// 密码
var regPasswordSpecial = /[~!@#%&=;':",./<>_\}\]\-\$\(\)\*\+\.\[\?\\\^\{\|]/;
var regPasswordAlpha = /[a-zA-Z]/;
var regPasswordNum = /[0-9]/;
var modifyPassword;

var checkModifiedPassword = true;
var checkModifiedPasswordConfirm = false;

// 密码匹配
// 匹配字母、数字、特殊字符至少两种的函数
function atLeastTwo(password) {
	var a = regPasswordSpecial.test(password) ? 1 : 0;
	var b = regPasswordAlpha.test(password) ? 1 : 0;
	var c = regPasswordNum.test(password) ? 1 : 0;
	return a + b + c;
}

// 修改密码验证
$(document).ready(function() {
	// 检验长短以及是否包含多种特殊字符
	$('#modified-password-div').find('#modified-password').change(function() {
		modifyPassword = $(this).val();
		if (modifyPassword.length < 8) {
			fail($("#modified-password-div"), '密码太短，不能少于8个字符');
			checkModifiedPassword = false;
		} else {
			if (atLeastTwo(modifyPassword) < 2) {
				fail($("#modified-password-div"), '密码中至少包含字母、数字、特殊字符的两种');
				checkModifiedPassword = false;
			} else {
				success($("#modified-password-div"));
				checkModifiedPassword = true;
			}
		}
	});

});

$(document)
		.ready(
				function() {
					// 再次输入密码校验
					$('#modified-passwordConfirm-div')
							.find('#modified-passwordConfirm')
							.change(
									function() {
										if ($(this).val() == modifyPassword) {
											success($("#modified-passwordConfirm-div"));
											checkModifiedPasswordConfirm = true;
										} else {
											fail(
													$("#modified-passwordConfirm-div"),
													'两次输入的密码不一致');
											checkModifiedPasswordConfirm = false;
										}
									});
				});

// 手机号码
var regTel = /^[0-9]{11}$/;
var checkModifiedTel = true;
$(document).ready(function() {
	$('#modified-tel-div').find("#modified-tel").change(function() {
		if (regTel.test($(this).val())) {
			success($("#modified-tel-div"));
			checkModifiedTel = true;
		} else {
			fail($("#modified-tel-div"), '手机号码只能为11位数字');
			checkModifiedTel = false;
		}
	});
});

// 邮箱
var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
var checkModifiedEmail = true;
$(document).ready(function() {
	$('#modified-email-div').find("#modified-email").change(function() {
		if (regEmail.test($(this).val())) {
			success($("#modified-email-div"));
			checkModifiedEmail = true;
		} else {
			fail($("#modified-email-div"), '邮箱格式错误,正确格式如:123312@qq.com');
			checkModifiedEmail = false;
		}
	});
});

// 修改个人信息提交检验
function checkModifyInfo() {
	if (checkModifiedTel == true) {
		success($("#modified-tel-div"));
	} else {
		fail($("#modified-tel-div"));
	}

	if (checkModifiedEmail == true) {
		success($("#modified-email-div"));
	} else {
		fail($("#modified-email-div"));
	}
	if (checkModifiedTel == true && checkModifiedEmail == true) {
		return true;
	} else {
		event.preventDefault();
		return false;
	}
};

// 提交验证修改密码表单,决定是否提交
function checkModifyPassword() {
	if (checkModifiedPassword == true) {
		success($("#modified-password-div"));
	} else {
		fail($("#modified-password-div"));
	}

	if (checkModifiedPasswordConfirm == true) {
		success($("#modified-passwordConfirm-div"));
	} else {
		fail($("#modified-passwordConfirm-div"));
	}

	if (checkModifiedPassword == true && checkModifiedPasswordConfirm == true) {
		return true;
	} else {
		event.preventDefault();
		return false;
	}
};
