"""
Simulación de un sistema de emergencias utilizando SimPy.

Este script simula el flujo de pacientes en un hospital, considerando procesos como triage,
consulta médica y rayos X. Los pacientes tienen diferentes niveles de severidad que afectan
su prioridad en el sistema.

@autor Javier Alvarado
"""

import simpy
import random
import matplotlib.pyplot as plt

# Configuración inicial
random.seed(10)  # Para resultados reproducibles
TIEMPO_TRIAGE = 10
TIEMPO_CONSULTA = 20
TIEMPO_RAYOS_X = 15
INTERVALO_LLEGADA = 5  # Promedio de llegada de pacientes

# Lista para almacenar tiempos de espera
tiempos_espera = []

def paciente(env, nombre, severidad, recursos):
    """
    Proceso que simula el flujo de un paciente en el sistema de emergencias.

    @param env: El entorno de simulación de SimPy.
    @param nombre: El nombre del paciente.
    @param severidad: La severidad del paciente (1 = alta prioridad, 5 = baja prioridad).
    @param recursos: Diccionario con los recursos disponibles (enfermeras, doctores, rayos X).
    """
    llegada = env.now
    print(f"{nombre} llega al tiempo {llegada}, severidad {severidad}")

    # Triage
    with recursos['enfermeras'].request(priority=severidad) as req:
        yield req
        yield env.timeout(TIEMPO_TRIAGE)

    # Consulta médica
    with recursos['doctores'].request(priority=severidad) as req:
        yield req
        yield env.timeout(TIEMPO_CONSULTA)

    # Rayos X (50% de los pacientes lo necesitan)
    if random.random() < 0.5:
        with recursos['rayosX'].request(priority=severidad) as req:
            yield req
            yield env.timeout(TIEMPO_RAYOS_X)

    tiempo_total = env.now - llegada
    tiempos_espera.append(tiempo_total)
    print(f"{nombre} termina al tiempo {env.now}, tiempo total: {tiempo_total}")

def simular(env, num_enfermeras, num_doctores, num_rayosX):
    """
    Configura y ejecuta la simulación del sistema de emergencias.

    @param env: El entorno de simulación de SimPy.
    @param num_enfermeras: Número de enfermeras disponibles.
    @param num_doctores: Número de doctores disponibles.
    @param num_rayosX: Número de máquinas de rayos X disponibles.
    """
    recursos = {
        'enfermeras': simpy.PriorityResource(env, capacity=num_enfermeras),
        'doctores': simpy.PriorityResource(env, capacity=num_doctores),
        'rayosX': simpy.PriorityResource(env, capacity=num_rayosX)
    }
    
    i = 0
    while True:
        yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADA))
        severidad = random.randint(1, 5)
        env.process(paciente(env, f"Paciente {i}", severidad, recursos))
        i += 1

# Ejecutar simulación
if __name__ == "__main__":
    """
    Punto de entrada principal para ejecutar la simulación.
    """
    env = simpy.Environment()
    env.process(simular(env, num_enfermeras=2, num_doctores=3, num_rayosX=1))
    env.run(until=100)

    # Análisis
    print(f"Tiempo promedio de espera: {sum(tiempos_espera) / len(tiempos_espera):.2f}")
    plt.hist(tiempos_espera, bins=10)
    plt.xlabel("Tiempo de espera")
    plt.ylabel("Frecuencia")
    plt.title("Distribución de tiempos de espera")
    plt.show()