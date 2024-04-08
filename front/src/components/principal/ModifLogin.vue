<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertLogin = reactive({
        id:route.query.id,
        
    });

    const responseInsertLogin = reactive({});
    
    

    onMounted(() => {
        if(!localStorage.getItem("tokenLogin")) {
            router.push("/");
        }
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/login/insertLogin';
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.post(url, objectInsertLogin, { headers });
            Object.assign(responseInsertLogin, response);
            router.push("/allLogin");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la login" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertLogin.mail" type="text">
<input v-model="objectInsertLogin.mdp" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
