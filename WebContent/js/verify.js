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

// 验证码
var settings = {
	e : 'idcode',
	codeType : {
		name : 'follow',
		len : 4
	}, // len是修改验证码长度的
	codeTip : '换个验证码?',
	inputID : 'idcode-btn' // 验证元素的ID
};

var _set = {
	storeLable : 'codeval',
	store : '#ehong-code-input',
	codeval : '#ehong-code'
}

$(document).ready(
		function() {
			$.idcode = {
				getCode : function(option) {
					_commSetting(option);
					return _storeData(_set.storeLable, null);
				},
				setCode : function(option) {
					_commSetting(option);
					_setCodeStyle("#" + settings.e, settings.codeType.name,
							settings.codeType.len);

				},
				validateCode : function(option) {
					_commSetting(option);
					var inputV;
					if (settings.inputID) {
						inputV = $('#' + settings.inputID).val();

					} else {
						inputV = $(_set.store).val();
					}
					if (inputV.toUpperCase() == _storeData(_set.storeLable,
							null).toUpperCase()) { // 修改的不区分大小写
						return true;
					} else {
						_setCodeStyle("#" + settings.e, settings.codeType.name,
								settings.codeType.len);
						return false;
					}
				}
			}
		});

function _commSetting(option) {
	$.extend(settings, option);
}

function _storeData(dataLabel, data) {
	var store = $(_set.codeval).get(0);
	if (data) {
		$.data(store, dataLabel, data);
	} else {
		return $.data(store, dataLabel);
	}
}

function _setCodeStyle(eid, codeType, codeLength) {
	var codeObj = _createCode(settings.codeType.name, settings.codeType.len);
	var randNum = Math.floor(Math.random() * 6);
	var htmlCode = ''
	if (!settings.inputID) {
		htmlCode = '<span><input id="ehong-code-input" type="text" maxlength="4" /></span>';
	}
	htmlCode += '<div id="ehong-code" class="ehong-idcode-val ehong-idcode-val';
	htmlCode += String(randNum);
	htmlCode += '" href="#" onblur="return false" onfocus="return false" oncontextmenu="return false" onclick="$.idcode.setCode()">'
			+ _setStyle(codeObj)
			+ '</div>'
			+ '<span id="ehong-code-tip-ck" class="ehong-code-val-tip" onclick="$.idcode.setCode()">'
			+ settings.codeTip + '</span>';
	$(eid).html(htmlCode);
	_storeData(_set.storeLable, codeObj);
}

function _setStyle(codeObj) {
	var fnCodeObj = new Array();
	var col = new Array('#BF0C43', '#E69A2A', '#707F02', '#18975F', '#BC3087',
			'#73C841', '#780320', '#90719B', '#1F72D8', '#D6A03C', '#6B486E',
			'#243F5F', '#16BDB5');
	var charIndex;
	for (var i = 0; i < codeObj.length; i++) {
		charIndex = Math.floor(Math.random() * col.length);
		fnCodeObj.push('<font color="' + col[charIndex] + '">'
				+ codeObj.charAt(i) + '</font>');
	}
	return fnCodeObj.join('');
}

function _createCode(codeType, codeLength) {
	var codeObj;
	if (codeType == 'follow') {
		codeObj = _createCodeFollow(codeLength);
	} else if (codeType == 'calc') {
		codeObj = _createCodeCalc(codeLength);
	} else {
		codeObj = "";
	}
	return codeObj;
}

function _createCodeCalc(codeLength) {
	var code1, code2, codeResult;
	var selectChar = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
	var charIndex;
	for (var i = 0; i < codeLength; i++) {
		charIndex = Math.floor(Math.random() * selectChar.length);
		code1 += selectChar[charIndex];

		charIndex = Math.floor(Math.random() * selectChar.length);
		code2 += selectChar[charIndex];
	}
	return [ parseInt(code1), parseInt(code2),
			parseInt(code1) + parseInt(code2) ];
}

function _createCodeFollow(codeLength) {
	var code = "";
	var selectChar = new Array('0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z');

	for (var i = 0; i < codeLength; i++) {
		var charIndex = Math.floor(Math.random() * selectChar.length);
		if (charIndex % 2 == 0) {
			code += selectChar[charIndex].toLowerCase();
		} else {
			code += selectChar[charIndex];
		}
	}
	return code;
}

var checkIdcode = false;
$(document).ready(function() {
	// 验证码
	$.idcode.setCode();

	$('#idcode-btn').change(function() {
		var IsBy = $.idcode.validateCode();
		if (IsBy) {
			success($("#idcode-btn-div"));
			checkIdcode = true;
		} else {
			fail($("#idcode-btn-div"), '验证码输入错误');
			checkIdcode = false;
		}
	});
});

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

// 修改密码验证
$(document).ready(function() {
	// 检验长短以及是否包含多种特殊字符
	$('#modified-password-div').find('#modified-password').change(function() {
		password = $(this).val();
		if (password.length < 8) {
			fail($("#modified-password-div"), '密码太短，不能少于8个字符');
			checkModifiedPassword = false;
		} else {
			if (atLeastTwo(password) < 2) {
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
										if ($(this).val() == password) {
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
var checkRegisterTel = false;
var checkModifiedTel = true;
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
var checkRegisterEmail = false;
var checkModifiedEmail = true;
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

$(document).ready(function() {

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

// 添加商品
var checkAddItemName = false;
var checkAddItemStock = false;
var checkAddItemPrice = false;

$(document).ready(function() {
	$('#add-item-name-div').find('#add-item-name').change(function() {
		if ($(this).val() == 0) {
			fail($("#add-item-name-div"), '餐点名不能为空');
			checkAddItemName = false;
		} else {
			success($("#add-item-name-div"));
			checkAddItemName = true;
		}
	});
});

var regPositiveInt = /^[1-9]\d*$/;
$(document).ready(function() {
	$('#add-item-stock-div').find('#add-item-stock').change(function() {
		if (regPositiveInt.test($(this).val())) {
			success($("#add-item-stock-div"));
			checkAddItemStock = true;
		} else {
			fail($("#add-item-stock-div"), '库存数量必须为正整数');
			checkAddItemStock = false;
		}
	});
});

var regPositiveDbl = /^([1-9]+(\.\d+)?|0\.\d+)$/;
$(document).ready(function() {
	$('#add-item-price-div').find('#add-item-price').change(function() {
		if (regPositiveInt.test($(this).val())) {
			success($("#add-item-price-div"));
			checkAddItemPrice = true;
		} else {
			fail($("#add-item-price-div"), '单价必须为正数');
			checkAddItemPrice = false;
		}
	});
});

// 验证添加商品表单,决定是否提交
function checkAddItem() {
	if (checkAddItemName == true) {
		success($("#add-item-name-div"));
	} else {
		fail($("#add-item-name-div"));
	}

	if (checkAddItemStock == true) {
		success($("#add-item-stock-div"));
	} else {
		fail($("#add-item-stock-div"));
	}

	if (checkAddItemPrice == true) {
		success($("#add-item-price-div"));
	} else {
		fail($("#add-item-price-div"));
	}

	if (checkAddItemName == true && checkAddItemStock == true
			&& checkAddItemPrice == true) {
		return true;
	} else {
		event.preventDefault();
		return false;
	}
};
