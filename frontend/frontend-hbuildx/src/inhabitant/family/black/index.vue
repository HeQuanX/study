<template>
	<ul class="mui-table-view">
		<router-link to="/black/base-query" tag="span">
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right">姓名/身份证查询</a>
			</li>
		</router-link>
		<router-link to="/black/custom-query" tag="span">
			<li class="mui-table-view-cell">
				<a class="mui-navigate-right">条件查询</a>
			</li>
		</router-link>
	</ul>
</template>
<script>
	module.exports = {
		data() {
			return {};
		},
		activated() {
			document.querySelector('#infortype').innerText='居民档案-黑名单'
		},
		methods: {
		}
	}
</script>
<style scoped>
</style>
