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

