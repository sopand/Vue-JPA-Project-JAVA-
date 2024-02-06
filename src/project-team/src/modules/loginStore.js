import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
const store = createStore({
	state() {
		return {
			account: {
				id: 0,
			},
		};
	},
	mutations: {
		setAccount(state, payload) {
			state.account.id = payload;
		},
	},
	modules: {},
	plugins: [createPersistedState()],
});

export default store;
