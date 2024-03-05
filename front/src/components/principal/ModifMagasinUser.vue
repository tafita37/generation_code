<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertMagasinUser = reactive({
        idMagasinUser:route.query.idMagasinUser,
        
    });

    const responseInsertMagasinUser = reactive({});
    
    

    onMounted(() => {
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/magasinUser/insertMagasinUser';
            const response = await axios.post(url, objectInsertMagasinUser);
            Object.assign(responseInsertMagasinUser, response);
            router.push("/allMagasinUser");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la magasinUser" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertMagasinUser.nomMagasinUser" type="text">
<input v-model="objectInsertMagasinUser.mdp" type="text">
<input v-model="objectInsertMagasinUser.role" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
