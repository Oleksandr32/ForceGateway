package com.oleksandrlysun.forcegateway.data.mappers

abstract class Mapper<T1, T2> {

	abstract fun toDomainModel(value: T1): T2

	fun toDomainModel(values: List<T1>): List<T2> {
		return values.map { value -> toDomainModel(value) }
	}
}