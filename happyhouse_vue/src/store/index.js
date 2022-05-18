import Vue from 'vue';
import Vuex from 'vuex';
import axios from 'axios';

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		user: null,
		qnaList: [],
		qnaSelect: null
	},
	getters: {
		qnaListLength: function (state) {
			return state.qnaList.length;
		}
	},
	mutations: {
		SET_QNA_LIST: function (state, qnaList) {
			state.qnaList = qnaList;
		},
		SET_GNA_SELECT: function (state, qnaSelect) {
			state.qnaSelect = qnaSelect;
		}
	},
	actions: {
		setQnaList: async function ({ commit }) {
			let res = await axios({
				url: 'http://localhost:9999/vue/board',
				method: 'GET'
			});
			console.log('SET_QNA_LIST', res.data);
			commit('SET_QNA_LIST', res.data);
		},
		setQnaSelect: async function ({ commit }, id) {
			return axios({
				url: 'http://localhost:9999/vue/board/' + id,
				method: 'GET'
			}).then(res=>{
                console.log('SET_GNA_SELECT', res.data);
                commit('SET_GNA_SELECT', res.data);
            }).catch(err =>{
                console.log("Internal Error: " , err);
            });;			
		}
	},
	modules: {}
});
