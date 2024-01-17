<template>
    <el-pagination
            background
            :current-page="page"
            @current-change="handleCurrentChange"
            layout="prev, pager, next, total"
            :total="total">
    </el-pagination>
</template>

<script>
    export default {
        name: "RPagination",
        props: ['page', 'total'],
        methods: {
            handleCurrentChange(page) {
                this.$emit('handleCurrentChange', page);
            }
        }
    }
</script>

<style scoped>

</style>