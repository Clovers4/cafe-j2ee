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

// 修改商品
var checkModifyItemName = true;
var checkModifyItemStock = true;
var checkModifyItemPrice = true;

$(document).ready(function() {
	$('#modify-item-name-div').find('#modify-item-name').change(function() {
		if ($(this).val() == 0) {
			fail($("#modify-item-name-div"), '餐点名不能为空');
			checkModifyItemName = false;
		} else {
			success($("#modify-item-name-div"));
			checkModifyItemName = true;
		}
	});
});

var regPositiveInt = /^[1-9]\d*$/;
$(document).ready(function() {
	$('#modify-item-stock-div').find('#modify-item-stock').change(function() {
		if (regPositiveInt.test($(this).val())) {
			success($("#modify-item-stock-div"));
			checkModifyItemStock = true;
		} else {
			fail($("#modify-item-stock-div"), '库存数量必须为正整数');
			checkModifyItemStock = false;
		}
	});
});

var regPositiveDbl = /^([1-9]+(\.\d+)?|0\.\d+)$/;
$(document).ready(function() {
	$('#modify-item-price-div').find('#modify-item-price').change(function() {
		if (regPositiveInt.test($(this).val())) {
			success($("#modify-item-price-div"));
			checkModifyItemPrice = true;
		} else {
			fail($("#modify-item-price-div"), '单价必须为正数');
			checkModifyItemPrice = false;
		}
	});
});

// 验证修改商品表单,决定是否提交
function checkModifyItem() {
	if (checkModifyItemName == true) {
		success($("#modify-item-name-div"));
	} else {
		fail($("#modify-item-name-div"));
	}

	if (checkModifyItemStock == true) {
		success($("#modify-item-stock-div"));
	} else {
		fail($("#modify-item-stock-div"));
	}

	if (checkModifyItemPrice == true) {
		success($("#modify-item-price-div"));
	} else {
		fail($("#modify-item-price-div"));
	}

	if (checkModifyItemName == true && checkModifyItemStock == true
			&& checkModifyItemPrice == true) {
		return true;
	} else {
		event.preventDefault();
		return false;
	}
};
