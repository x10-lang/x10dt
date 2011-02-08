#ifndef __X10_ARRAY_POLYSCANNER__POINTIT_H
#define __X10_ARRAY_POLYSCANNER__POINTIT_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_ITERATOR_H_NODEPS
#include <x10/lang/Iterator.h>
#undef X10_LANG_ITERATOR_H_NODEPS
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace array { 
class PolyScanner;
} } 
namespace x10 { namespace array { 
class PolyScanner__RailIt;
} } 
namespace x10 { namespace lang { 
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace array { 

class PolyScanner__PointIt : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[3];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::lang::Iterator<x10aux::ref<x10::array::Point> >::itable<x10::array::PolyScanner__PointIt > _itable_0;
    
    static x10::lang::Any::itable<x10::array::PolyScanner__PointIt > _itable_1;
    
    void _instance_init();
    
    x10aux::ref<x10::array::PolyScanner> FMGL(out__);
    
    x10aux::ref<x10::array::PolyScanner__RailIt> FMGL(it);
    
    void _constructor(x10aux::ref<x10::array::PolyScanner> out__);
    
    static x10aux::ref<x10::array::PolyScanner__PointIt> _make(x10aux::ref<x10::array::PolyScanner> out__);
    
    virtual x10_boolean hasNext();
    virtual x10aux::ref<x10::array::Point> next();
    virtual void remove();
    virtual x10aux::ref<x10::array::PolyScanner__PointIt> x10__array__PolyScanner__PointIt____x10__array__PolyScanner__PointIt__this(
      );
    virtual x10aux::ref<x10::array::PolyScanner> x10__array__PolyScanner__PointIt____x10__array__PolyScanner__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_ARRAY_POLYSCANNER__POINTIT_H

namespace x10 { namespace array { 
class PolyScanner__PointIt;
} } 

#ifndef X10_ARRAY_POLYSCANNER__POINTIT_H_NODEPS
#define X10_ARRAY_POLYSCANNER__POINTIT_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Iterator.h>
#include <x10/array/Point.h>
#include <x10/array/PolyScanner.h>
#include <x10/array/PolyScanner__RailIt.h>
#include <x10/lang/Boolean.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#ifndef X10_ARRAY_POLYSCANNER__POINTIT_H_GENERICS
#define X10_ARRAY_POLYSCANNER__POINTIT_H_GENERICS
template<class __T> x10aux::ref<__T> x10::array::PolyScanner__PointIt::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::array::PolyScanner__PointIt> this_ = new (memset(x10aux::alloc<x10::array::PolyScanner__PointIt>(), 0, sizeof(x10::array::PolyScanner__PointIt))) x10::array::PolyScanner__PointIt();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_ARRAY_POLYSCANNER__POINTIT_H_GENERICS
#endif // __X10_ARRAY_POLYSCANNER__POINTIT_H_NODEPS
