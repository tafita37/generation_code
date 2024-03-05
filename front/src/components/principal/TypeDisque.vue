<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allTypeDisque=reactive({});

    const getAllTypeDisque = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/typeDisque/allTypeDisque';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allTypeDisque, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idTypeDisque) => {
        router.push({path : "/modifTypeDisque", query: {idTypeDisque : idTypeDisque}});
    }

    const deleteTypeDisqueById = (idTypeDisque) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/typeDisque/deleteTypeDisqueById?idTypeDisque='+idTypeDisque;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllTypeDisque);
</script>
<template>
    <TitleListe title-liste="Liste des typeDisque"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(typeDisque)  in allTypeDisque" :key="typeDisque.idTypeDisque">
            <H5TitleListe labels="" :title-h5="typeDisque.idTypeDisque"/>
            <SpanListe :title-span='typeDisque.idTypeDisque' labels=''/><SpanListe :title-span='typeDisque.nomTypeDisque' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(typeDisque.idTypeDisque)" :funct-delete="() => deleteTypeDisqueById(typeDisque.idTypeDisque)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
