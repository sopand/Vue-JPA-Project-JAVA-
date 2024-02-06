<template>
	<div class="editor_box">
		<label>{{ props.label }}</label>
		<div>
			<QuillEditor
				style="min-height: 300px"
				:modules="modules"
				toolbar="full"
				@update:content="updateContent"
			/>
		</div>
	</div>
</template>
<script setup>
import { reactive } from 'vue';
import ImageUploader from 'quill-image-uploader';
import { QuillDeltaToHtmlConverter } from 'quill-delta-to-html';
// const props = defineProps(['label', 'imageList']);
const props = defineProps(['label']);
// const imagelist = reactive(props.imageList);
const emit = defineEmits(['update:value']);
const editContent = reactive({
	text: '',
});

const updateContent = content => {
	const converter = new QuillDeltaToHtmlConverter(content.ops, {});
	const html = converter.convert();
	editContent.text = html;
	emit('update:value', editContent.text);
};
const modules = {
	name: 'imageUploader',
	module: ImageUploader,
	options: {
		// upload: file => {
		// 	return new Promise((resolve, reject) => {
		// 		try {
		// 			imagelist.push(file);
		// 		} catch (error) {
		// 			reject('Failed to upload');
		// 		}
		// 	});
		// },
	},
};
</script>

<style scoped>
label {
	width: 70px;
	font-weight: bold;
	height: 100%;
	font-size: 18px;
}
.editor_box,
label {
	display: flex;
	align-items: center;
	justify-content: center;
}
</style>
