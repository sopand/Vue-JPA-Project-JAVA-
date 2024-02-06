<template>
	<button
		type="button"
		:style="{
			width: childWidth,
			backgroundColor: childColor,
			height: childHeight,
		}"
	>
		{{ button_tag }}
	</button>
</template>

<script setup>
defineProps({
	button_tag: String,
	childWidth: String,
	childColor: String,
	childHeight: String,
});
</script>

<style scoped>
button:hover {
	cursor: pointer;
}
button {
	width: 350px;
	height: 40px;
	border-radius: 10px;
	color: white;
	font-size: 18px;
	font-weight: bold;
	border: 1px solid #e2e2e2;
	background-color: #86a7fc;
}
</style>
