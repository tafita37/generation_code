<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertTest = reactive({
        idtest:route.query.idtest,
        
    });

    const responseInsertTest = reactive({});
    
    

    onMounted(() => {
        if(!localStorage.getItem("tokenLogin")) {
            router.push("/");
        }
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/test/insertTest';
            const token=localStorage.getItem("tokenLogin");
            const headers = {
                Authorization: `Bearer ${token}`,
            };
            const response = await axios.post(url, objectInsertTest, { headers });
            Object.assign(responseInsertTest, response);
            router.push("/allTest");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la test" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertTest.nomtest" type="text">
<input v-model="objectInsertTest.label" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
