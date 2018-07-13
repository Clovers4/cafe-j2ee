var regNum = /^[0-9]+$/;

// 保证购买数量一定是数字,这里没有做最大值验证，若用户直接输入100（库存30）也是被允许的，
$(document).ready(function() {
	$('#item-number').change(function() {
		if (regNum.test($(this).val()) == false) {
			$(this).val(1);
		}
		if (Number($(this).val()) < 1) {
			$(this).val(1);
		}
	});
});

function increaseItemNum(max) {
	var num = Number($('#item-number').val()) + 1;
	if (num > max) {
		num = max;
	}
	$('#item-number').val(num);
}

function decreaseItemNum() {
	var num = Number($('#item-number').val()) - 1;
	if (num < 1) {
		num = 1;
	}
	$('#item-number').val(num);
}