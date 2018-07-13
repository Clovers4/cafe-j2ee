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
