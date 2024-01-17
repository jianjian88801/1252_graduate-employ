<template>
    <div id="app"  v-title data-title="毕业生就业服务平台">
        <router-view></router-view>
    </div>
</template>

<script>

    export default {
        name: 'App',
        components: {}
    }
</script>

<style>
    html, body {
        height: 100%;
        margin: 0;
        padding: 0;
    }

    #app {
        height: 100%;
    }
</style>
