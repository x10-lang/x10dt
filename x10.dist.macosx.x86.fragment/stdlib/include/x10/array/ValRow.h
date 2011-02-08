#ifndef __X10_ARRAY_VALROW_H
#define __X10_ARRAY_VALROW_H

#include <x10rt.h>


#define X10_ARRAY_ROW_H_NODEPS
#include <x10/array/Row.h>
#undef X10_ARRAY_ROW_H_NODEPS
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Rail;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class IllegalOperationException;
} } 
namespace x10 { namespace array { 

class ValRow : public x10::array::Row   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Fun_0_1<x10_int, x10_int>::itable<x10::array::ValRow > _itable_0;
    
    static x10::lang::Any::itable<x10::array::ValRow > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<x10_int> > FMGL(row);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_int> > row);
    
    static x10aux::ref<x10::array::ValRow> _make(x10aux::ref<x10::array::Array<x10_int> > row);
    
    void _constructor(x10aux::ref<x10::lang::Rail<x10_int > > row);
    
    static x10aux::ref<x10::array::ValRow> _make(x10aux::ref<x10::lang::Rail<x10_int > > row);
    
    void _constructor(x10_int cols, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > init);
    
    static x10aux::ref<x10::array::ValRow> _make(x10_int cols, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > init);
    
    virtual x10_int __apply(x10_int i);
    virtual x10_int __set(x10_int v, x10_int i);
    virtual x10aux::ref<x10::array::ValRow> x10__array__ValRow____x10__array__ValRow__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_VALROW_H

namespace x10 { namespace array { 
class ValRow;
} } 

#ifndef X10_ARRAY_VALROW_H_NODEPS
#define X10_ARRAY_VALROW_H_NODEPS
#include <x10/array/Row.h>
#include <x10/array/Array.h>
#include <x10/lang/Int.h>
#include <x10/lang/Rail.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/IllegalOperationException.h>
#ifndef X10_ARRAY_VALROW_H_GENERICS
#define X10_ARRAY_VALROW_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::ValRow::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::ValRow> this_ = new (memset(x10aux::alloc<x10::array::ValRow>(), 0, sizeof(x10::array::ValRow))) x10::array::ValRow();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_VALROW_H_GENERICS
#endif // __X10_ARRAY_VALROW_H_NODEPS
