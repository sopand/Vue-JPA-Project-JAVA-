import './assets/main.css';

import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from '@/modules/loginStore';
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
const app = createApp(App);

app.config.globalProperties.$formatDateTimeArray = function (dateTimeArray) {
	// 배열이 유효한지 확인
	if (Array.isArray(dateTimeArray) && dateTimeArray.length === 7) {
		const [year, month, day, hours, minutes, seconds, milliseconds] =
			dateTimeArray;

		const dateObject = new Date(
			Date.UTC(
				year,
				month - 1,
				day,
				hours,
				minutes,
				seconds,
				milliseconds / 1e6,
			),
		);

		const formattedDate = dateObject
			.toISOString()
			.slice(0, 19)
			.replace('T', ' '); // ISO 8601 형식으로 변환 후 자르기

		return formattedDate;
	} else {
		console.error('유효하지 않은 날짜 및 시간 형식:', dateTimeArray);
		return 'Invalid Date';
	}
};

app.component('QuillEditor', QuillEditor);
app.use(router);
app.use(store);
app.mount('#app');
