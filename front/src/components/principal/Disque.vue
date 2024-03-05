<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allDisque=reactive({});

    const getAllDisque = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/disque/allDisque';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allDisque, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idDisque) => {
        router.push({path : "/modifDisque", query: {idDisque : idDisque}});
    }

    const deleteDisqueById = (idDisque) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/disque/deleteDisqueById?idDisque='+idDisque;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllDisque);
</script>
<template>
    <TitleListe title-liste="Liste des disque"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(disque)  in allDisque" :key="disque.idDisque">
            <H5TitleListe labels="" :title-h5="disque.idDisque"/>
            <SpanListe :title-span='disque.idDisque' labels=''/><SpanListe :title-span='disque.capacite' labels=''/><SpanListe :title-span='disque.vitesse' labels=''/><SpanListe :title-span='disque.idTypeDisque.label' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(disque.idDisque)" :funct-delete="() => deleteDisqueById(disque.idDisque)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
