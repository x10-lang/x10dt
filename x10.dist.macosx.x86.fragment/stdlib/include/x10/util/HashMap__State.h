#ifndef __X10_UTIL_HASHMAP__STATE_H
#define __X10_UTIL_HASHMAP__STATE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace util { 
template<class FMGL(K), class FMGL(V)> class HashMap;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterator;
} } 
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)> class HashMap__HashEntry;
} } 
namespace x10 { namespace util { 

template<class FMGL(Key), class FMGL(Value)> class HashMap__State;
template <> class HashMap__State<void, void>;
template<class FMGL(Key), class FMGL(Value)> class HashMap__State : public x10::lang::Object
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<x10::util::Pair<FMGL(Key), FMGL(Value)> > >
      FMGL(content);
    
    void _constructor(x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > map);
    
    static x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> > _make(
             x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > map);
    
    virtual x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> >
      x10__util__HashMap__State____x10__util__HashMap__State__this(
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
template <> class HashMap__State<void, void> : public x10::lang::Object
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_UTIL_HASHMAP__STATE_H

namespace x10 { namespace util { 
template<class FMGL(Key), class FMGL(Value)>
class HashMap__State;
} } 

#ifndef X10_UTIL_HASHMAP__STATE_H_NODEPS
#define X10_UTIL_HASHMAP__STATE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/array/Array.h>
#include <x10/util/Pair.h>
#include <x10/util/HashMap.h>
#include <x10/lang/Int.h>
#include <x10/lang/Iterator.h>
#include <x10/util/HashMap__HashEntry.h>
#include <x10/array/Array.h>
#include <x10/util/Pair.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/util/Pair.h>
#include <x10/util/Pair.h>
#include <x10/util/HashMap__HashEntry.h>
#ifndef X10_UTIL_HASHMAP__STATE__CLOSURE__0_CLOSURE
#define X10_UTIL_HASHMAP__STATE__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_1.h>
template<class FMGL(Key), class FMGL(Value)> class x10_util_HashMap__State__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> >::template itable <x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10::util::Pair<FMGL(Key), FMGL(Value)> __apply(x10_int p) {
        
        //#line 312 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > entry =
          x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > >::next(x10aux::nullCheck(it));
        
        //#line 313 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
        return x10::util::Pair_methods<FMGL(Key), FMGL(Value)>::_make(x10aux::nullCheck(entry)->getKey(),
                                                                      x10aux::nullCheck(entry)->getValue());
        
    }
    
    // captured environment
    x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > > > it;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->it);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >* storage = x10aux::alloc<x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) > >();
        buf.record_reference(x10aux::ref<x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) > >(storage));
        x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > > > that_it = buf.read<x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > > > >();
        x10aux::ref<x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) > > this_ = new (storage) x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >(that_it);
        return this_;
    }
    
    x10_util_HashMap__State__closure__0(x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > > > it) : it(it) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10:311-314";
    }

};

template<class FMGL(Key), class FMGL(Value)> typename x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> >::template itable <x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) > >x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >::__apply, &x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >::toString, &x10::lang::Closure::typeName);template<class FMGL(Key), class FMGL(Value)>
x10aux::itable_entry x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> > >, &x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(Key), class FMGL(Value)>
const x10aux::serialization_id_t x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_UTIL_HASHMAP__STATE__CLOSURE__0_CLOSURE
#ifndef X10_UTIL_HASHMAP__STATE_H_GENERICS
#define X10_UTIL_HASHMAP__STATE_H_GENERICS
template<class FMGL(Key), class FMGL(Value)> template<class __T> x10aux::ref<__T> x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> >(), 0, sizeof(x10::util::HashMap__State<FMGL(Key), FMGL(Value)>))) x10::util::HashMap__State<FMGL(Key), FMGL(Value)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_UTIL_HASHMAP__STATE_H_GENERICS
#ifndef X10_UTIL_HASHMAP__STATE_H_IMPLEMENTATION
#define X10_UTIL_HASHMAP__STATE_H_IMPLEMENTATION
#include <x10/util/HashMap__State.h>


template<class FMGL(Key), class FMGL(Value)> void x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_instance_init() {
    _I_("Doing initialisation for class: x10::util::HashMap__State<FMGL(Key), FMGL(Value)>");
    
}


//#line 305 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10FieldDecl_c

//#line 307 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(Key), class FMGL(Value)> void x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_constructor(
                                               x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > map)
{
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 308 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10_int size =
      x10aux::nullCheck(map)->size();
    
    //#line 309 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::Iterator<x10aux::ref<x10::util::HashMap__HashEntry<FMGL(Key), FMGL(Value)> > > > it =
      x10aux::nullCheck(map)->entriesIterator();
    
    //#line 310 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> >)this)->
      FMGL(content) =
      x10::array::Array<x10::util::Pair<FMGL(Key), FMGL(Value)> >::_make(size,
                                                                         x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> > > >(x10aux::ref<x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> > >(x10aux::ref<x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value) > >(new (x10aux::alloc<x10::lang::Fun_0_1<x10_int, x10::util::Pair<FMGL(Key), FMGL(Value)> > >(sizeof(x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value)>)))x10_util_HashMap__State__closure__0<FMGL(Key),FMGL(Value)>(it)))));
    
}
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> > x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_make(
  x10aux::ref<x10::util::HashMap<FMGL(Key), FMGL(Value)> > map)
{
    x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> > this_ = new (memset(x10aux::alloc<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> >(), 0, sizeof(x10::util::HashMap__State<FMGL(Key), FMGL(Value)>))) x10::util::HashMap__State<FMGL(Key), FMGL(Value)>();
    this_->_constructor(map);
    return this_;
}



//#line 304 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10MethodDecl_c
template<class FMGL(Key), class FMGL(Value)>
x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> >
  x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::x10__util__HashMap__State____x10__util__HashMap__State__this(
  ) {
    
    //#line 304 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/util/HashMap.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::util::HashMap__State<FMGL(Key), FMGL(Value)> >)this);
    
}
template<class FMGL(Key), class FMGL(Value)>
const x10aux::serialization_id_t x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(Key), class FMGL(Value)>
void x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(content));
    
}

template<class FMGL(Key), class FMGL(Value)>
void x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(content) = buf.read<x10aux::ref<x10::array::Array<x10::util::Pair<FMGL(Key), FMGL(Value)> > > >();
}

template<class FMGL(Key), class FMGL(Value)>
x10aux::RuntimeType x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::rtt;
template<class FMGL(Key), class FMGL(Value)>
void x10::util::HashMap__State<FMGL(Key), FMGL(Value)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::util::HashMap__State<void, void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[2] = { x10aux::getRTT<FMGL(Key)>(), x10aux::getRTT<FMGL(Value)>()};
    x10aux::RuntimeType::Variance variances[2] = { x10aux::RuntimeType::invariant, x10aux::RuntimeType::invariant};
    const char *baseName = "x10.util.HashMap.State";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 2, params, variances);
}
#endif // X10_UTIL_HASHMAP__STATE_H_IMPLEMENTATION
#endif // __X10_UTIL_HASHMAP__STATE_H_NODEPS
