#ifndef __X10_ARRAY_POLYROW_H
#define __X10_ARRAY_POLYROW_H

#include <x10rt.h>


#define X10_ARRAY_VALROW_H_NODEPS
#include <x10/array/ValRow.h>
#undef X10_ARRAY_VALROW_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
class PolyRegion;
} } 
namespace x10 { namespace array { 
class PolyMat;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class Row;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace io { 
class Printer;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace array { 

class PolyRow : public x10::array::ValRow   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(rank);
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Fun_0_1<x10_int, x10_int>::itable<x10::array::PolyRow > _itable_0;
    
    static x10::lang::Any::itable<x10::array::PolyRow > _itable_1;
    
    void _instance_init();
    
    void _constructor(x10aux::ref<x10::array::Array<x10_int> > as_);
    
    static x10aux::ref<x10::array::PolyRow> _make(x10aux::ref<x10::array::Array<x10_int> > as_);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_int> > as_, x10_int n);
    
    static x10aux::ref<x10::array::PolyRow> _make(x10aux::ref<x10::array::Array<x10_int> > as_,
                                                  x10_int n);
    
    void _constructor(x10aux::ref<x10::array::Point> p, x10_int k);
    
    static x10aux::ref<x10::array::PolyRow> _make(x10aux::ref<x10::array::Point> p,
                                                  x10_int k);
    
    void _constructor(x10_int cols, x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > init);
    
    static x10aux::ref<x10::array::PolyRow> _make(x10_int cols,
                                                  x10aux::ref<x10::lang::Fun_0_1<x10_int, x10_int> > init);
    
    static x10_int compare(x10aux::ref<x10::array::Row> a,
                           x10aux::ref<x10::array::Row> b);
    virtual x10_boolean isParallel(x10aux::ref<x10::array::PolyRow> that);
    virtual x10_boolean isRect();
    virtual x10_boolean contains(x10aux::ref<x10::array::Point> p);
    virtual x10aux::ref<x10::array::PolyRow> complement();
    virtual void printEqn(x10aux::ref<x10::io::Printer> ps,
                          x10aux::ref<x10::lang::String> spc,
                          x10_int row);
    x10_int rank();
    virtual x10aux::ref<x10::array::PolyRow> x10__array__PolyRow____x10__array__PolyRow__this(
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
#endif // X10_ARRAY_POLYROW_H

namespace x10 { namespace array { 
class PolyRow;
} } 

#ifndef X10_ARRAY_POLYROW_H_NODEPS
#define X10_ARRAY_POLYROW_H_NODEPS
#include <x10/array/ValRow.h>
#include <x10/lang/Int.h>
#include <x10/array/PolyRegion.h>
#include <x10/array/PolyMat.h>
#include <x10/array/Array.h>
#include <x10/array/Point.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Row.h>
#include <x10/lang/Boolean.h>
#include <x10/io/Printer.h>
#include <x10/lang/String.h>
#ifndef X10_ARRAY_POLYROW_H_GENERICS
#define X10_ARRAY_POLYROW_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::PolyRow::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::PolyRow> this_ = new (memset(x10aux::alloc<x10::array::PolyRow>(), 0, sizeof(x10::array::PolyRow))) x10::array::PolyRow();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_POLYROW_H_GENERICS
#endif // __X10_ARRAY_POLYROW_H_NODEPS
